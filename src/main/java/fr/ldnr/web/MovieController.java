package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Movie;
import fr.ldnr.entities.MovieTheater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {

    @Autowired
    IBusinessImpl iBusinessImpl;

    private final Logger logger = LoggerFactory.getLogger(CityController.class);

    @GetMapping("/movie")
    public String movie(Model model, @RequestParam(name="page" , defaultValue = "0") int page,
                                     @RequestParam(name = "idMovieTheater", defaultValue = "0") Long idMovieTheater,
                                     @RequestParam(name = "keyword", defaultValue = "") String kw,
                                     @ModelAttribute(name="error") String error) {
        Page<Movie> movieList = null;

        if(idMovieTheater != 0) {
            Optional<MovieTheater> optionalMovieTheater = iBusinessImpl.findMovieTheaterById(idMovieTheater);
            if(!optionalMovieTheater.isPresent()) {
                model.addAttribute("error", "ID MOVIE THEATER INVALID");
                movieList = iBusinessImpl.findMovieByKeyword(kw, page);
            }else {
                movieList = iBusinessImpl.findMovieByMovieTheater(idMovieTheater, page);
            }
        }else {
            movieList = iBusinessImpl.findMovieByKeyword(kw, page);
        }
        model.addAttribute("keyword",kw);
        model.addAttribute("idMovieTheater",idMovieTheater);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", new int[movieList.getTotalPages()]);
        model.addAttribute("error", model.getAttribute("error"));
        model.addAttribute("movieList", movieList);
        return "movie";
    }

}
