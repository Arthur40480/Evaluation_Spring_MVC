package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    IBusinessImpl iBusinessImpl;

    private final Logger logger = LoggerFactory.getLogger(CityController.class);

    @GetMapping("/movie")
    public String movie(Model model, @RequestParam(name = "idMovieTheater", defaultValue = "0") Long idMovieTheater,
                                     @RequestParam(name = "keyword", defaultValue = "") String kw,
                                     @ModelAttribute(name="error") String error) {
        List<Movie> movieList = iBusinessImpl.findMovieByKeyword(kw);
        model.addAttribute("movieList", movieList);
        return "movie";
    }

}
