package fr.ldnr.web;

import fr.ldnr.dao.CityRepository;
import fr.ldnr.dao.MovieRepository;
import fr.ldnr.dao.MovieTheaterRepository;
import fr.ldnr.entities.City;
import fr.ldnr.entities.MovieTheater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MovieTheaterController {

    @Autowired
    CityRepository cityRepository;
    @Autowired
    MovieTheaterRepository movieTheaterRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<City> cityList = cityRepository.findAll();
        List<MovieTheater> movieTheaterList = movieTheaterRepository.findAll();

        model.addAttribute("cityList", cityList);
        model.addAttribute("movieTheaterList", movieTheaterList);
        return "movieTheaters";
    }
}
