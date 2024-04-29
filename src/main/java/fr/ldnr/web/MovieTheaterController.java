package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.dao.CityRepository;
import fr.ldnr.dao.MovieRepository;
import fr.ldnr.dao.MovieTheaterRepository;
import fr.ldnr.entities.City;
import fr.ldnr.entities.MovieTheater;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String index(Model model, @RequestParam(name = "idCity", defaultValue = "0") Long idCity,
                                     @RequestParam(name = "keyword", defaultValue = "") String kw,
                                     @ModelAttribute(name="error") String error) {
        List<City> cityList = iBusinessImpl.findAllCity();
        List<MovieTheater> movieTheaterList = null;

        if(idCity != 0) {
            Optional<City> optionalCity = iBusinessImpl.findCityById(idCity);
            if (!optionalCity.isPresent()) {
                model.addAttribute("error", "ID CITY INVALID");
                movieTheaterList = iBusinessImpl.findMovieTheaterByKeyword(kw);
            } else {
                movieTheaterList = iBusinessImpl.findMovieTheaterByCity(idCity);
            }
        }else {
            movieTheaterList = iBusinessImpl.findMovieTheaterByKeyword(kw);
        }

        model.addAttribute("error", model.getAttribute("error"));
        model.addAttribute("cityList", cityList);
        model.addAttribute("movieTheaterList", movieTheaterList);
        return "movieTheaters";
    }
}
