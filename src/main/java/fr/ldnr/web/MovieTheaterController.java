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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieTheaterController {

    @Autowired
    IBusinessImpl iBusinessImpl;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "idCity", defaultValue = "0") Long idCity) {
        List<City> cityList = iBusinessImpl.findAllCity();
        List<MovieTheater> movieTheaterList = null;

        if(idCity > 0) {
            movieTheaterList = iBusinessImpl.findMovieTheaterByCity(idCity);
        } else {
            movieTheaterList = iBusinessImpl.findAllMovieTheater();
        }

        model.addAttribute("cityList", cityList);
        model.addAttribute("movieTheaterList", movieTheaterList);
        return "movieTheaters";
    }
}
