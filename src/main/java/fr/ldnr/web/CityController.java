package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class CityController {

    @Autowired
    IBusinessImpl iBusinessImpl;

    @GetMapping("/city")
    public String city(Long idCity, Model model) {
        Optional<City> optionalCity = iBusinessImpl.findCityById(idCity);
        if(optionalCity.isPresent()) {
            City city = optionalCity.get();
            return "redirect:/index?idCity=" + city.getId();
        }
        return "redirect:/index?idCity=0";
    }
}
