package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.City;
import fr.ldnr.entities.MovieTheater;
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
public class MovieTheaterController {

    @Autowired
    IBusinessImpl iBusinessImpl;

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
}
