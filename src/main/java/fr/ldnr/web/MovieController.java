package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Movie;
import fr.ldnr.entities.MovieTheater;
import fr.ldnr.entities.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class MovieController {

    @Autowired
    IBusinessImpl iBusinessImpl;

    private final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/movie")
    public String movie(Model model, @RequestParam(name="page" , defaultValue = "0") int page,
                                     @RequestParam(name = "idMovieTheater", defaultValue = "0") Long idMovieTheater,
                                     @RequestParam(name = "keyword", defaultValue = "") String kw) {
        Page<Movie> movieList = null;

        if(idMovieTheater != 0) {
            MovieTheater optionalMovieTheater = iBusinessImpl.findMovieTheaterById(idMovieTheater);
            if(optionalMovieTheater == null) {
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

    @GetMapping("/movieForm")
    public String article(Model model) {
        model.addAttribute("movie" , new Movie());
        try {
            model.addAttribute("movieTheaterList", iBusinessImpl.findAllMovieTheater());
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            logger.error("[MOVIE CONTROLLER : MANAGE NEW MOVIE] : {} " , e.getMessage());
        }
        return "movieForm";
    }

    @GetMapping("/editMovie")
    public String edit(Long idMovie, Model model) {
        Movie movie;
        try {
            movie = iBusinessImpl.findMovieById(idMovie);
            model.addAttribute("movieTheaterList", iBusinessImpl.findAllMovieTheater());
            model.addAttribute("movie", movie);
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            logger.error("[MOVIE CONTROLLER : EDIT] : {} " , e.getMessage());
        }
        return "movieForm";
    }

    @PostMapping("/saveMovie")
    public String save(@Valid Movie movie, BindingResult bindingResult,
                       @RequestParam(name = "idMovie", defaultValue = "0") Long idMovie,
                       Model model, RedirectAttributes redirectAttrs) {
        try {
            if(bindingResult.hasErrors()) {
                model.addAttribute("movieTheaterList", iBusinessImpl.findAllMovieTheater());
                return "movieForm";
            }
            if (idMovie != null) {
                movie.setId(idMovie);
                iBusinessImpl.createMovie(movie);
            }else {
                iBusinessImpl.createMovie(movie);
            }
        }
        catch(Exception e) {
            redirectAttrs.addAttribute("error",e.getMessage());
            logger.error("[MOVIE CONTROLLER : SAVE MOVIE] : {} " , e.getMessage());
        }
        return "redirect:/movie";
    }

    @GetMapping("/deleteMovie")
    public String deleteCity(Long idMovie, Model model, RedirectAttributes redirectAttributes) {
        try {
            Movie movieToDelete = iBusinessImpl.findMovieById(idMovie);
            Page<Session> sessionList= iBusinessImpl.findSessionByMovie(idMovie, 0);
            if (sessionList.isEmpty()) {
                iBusinessImpl.deleteMovie(movieToDelete);
            }else {
                model.addAttribute("error", "SUPPRESSION IMPOSSIBLE : Des séances sont associées à ce film. Veuillez d'abord supprimer les séances affiliées avant de supprimer le film.");
            }

            Page<Movie> movieList = iBusinessImpl.findMovieByKeyword("", 0);
            model.addAttribute("movieList", movieList);
            model.addAttribute("keyword", "");
            model.addAttribute("currentPage", 0);
            model.addAttribute("pages", new int[movieList.getTotalPages()]);

        } catch (Exception e) {
            redirectAttributes.addAttribute("error",e.getMessage());
            logger.error("[MOVIE CONTROLLER : DELETE] : {} " , e.getMessage());
        }
        return "movie";
    }
}
