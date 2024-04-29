package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.City;
import fr.ldnr.entities.MovieTheater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class MovieTheaterController {

    @Autowired
    IBusinessImpl iBusinessImpl;

    private final Logger logger = LoggerFactory.getLogger(MovieTheaterController.class);

    @GetMapping("/index")
    public String index(Model model, @RequestParam(name="page" , defaultValue = "0") int page,
                                     @RequestParam(name = "idCity", defaultValue = "0") Long idCity,
                                     @RequestParam(name = "keyword", defaultValue = "") String kw,
                                     @ModelAttribute(name="error") String error) {
        List<City> cityList = iBusinessImpl.findAllCity();
        Page<MovieTheater> movieTheaterList = null;

        if(idCity != 0) {
            Optional<City> optionalCity = iBusinessImpl.findCityById(idCity);
            if (!optionalCity.isPresent()) {
                model.addAttribute("error", "ID CITY INVALID");
                movieTheaterList = iBusinessImpl.findMovieTheaterByKeyword(kw, page);
            }else {
                movieTheaterList = iBusinessImpl.findMovieTheaterByCity(idCity, page);
            }
        }else {
            movieTheaterList = iBusinessImpl.findMovieTheaterByKeyword(kw, page);
        }
        model.addAttribute("keyword",kw);
        model.addAttribute("idCity",idCity);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", new int[movieTheaterList.getTotalPages()]);
        model.addAttribute("error", model.getAttribute("error"));
        model.addAttribute("cityList", cityList);
        model.addAttribute("movieTheaterList", movieTheaterList);
        return "movieTheaters";
    }

    @GetMapping("/movieTheater")
    public String article(Model model) {
        model.addAttribute("movieTheater" , new MovieTheater());
        try {
            model.addAttribute("cityList", iBusinessImpl.findAllCity());
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            logger.error("[MOVIE_THEATER CONTROLLER : MANAGE NEW MOVIE_THEATER] : {} " , e.getMessage());
        }
        return "movieTheatersForm";
    }

    @PostMapping("/saveMovieTheater")
    public String save(@Valid MovieTheater movieTheater, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
        try {
            if(bindingResult.hasErrors()) {
                model.addAttribute("cityList", iBusinessImpl.findAllCity());
                return "movieTheatersForm";
            }
            iBusinessImpl.saveMovieTheater(movieTheater);
        }
        catch(Exception e) {
            redirectAttrs.addAttribute("error",e.getMessage());
            logger.error("[MOVIE_THEATER CONTROLLER : SAVE MOVIE_THEATER] : {} " , e.getMessage());
        }
        return "redirect:/index";
    }
}
