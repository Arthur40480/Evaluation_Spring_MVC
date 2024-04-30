package fr.ldnr.web;

import fr.ldnr.entities.Movie;
import fr.ldnr.entities.MovieTheater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.City;
import fr.ldnr.exceptions.ManageErrors;
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
import java.util.Optional;

@Controller
public class CityController {

    @Autowired
    IBusinessImpl iBusinessImpl;

    private final Logger logger = LoggerFactory.getLogger(CityController.class);

    @GetMapping("/city")
    public String city(Model model, @RequestParam(name="page" , defaultValue = "0") int page,
                                    @RequestParam(name = "keyword", defaultValue = "") String kw) {

        Page<City> cityList = iBusinessImpl.findCityByKeyword(kw, page);
        model.addAttribute("cityList", cityList);
        model.addAttribute("keyword", kw);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", new int[cityList.getTotalPages()]);
        return "city";
    }

    @GetMapping("/cityNavBar")
    public String cityNavBar(Long idCity, Model model) {
        Long cityId = (long) 0;
        try{
            City optionalCity = iBusinessImpl.findCityById(idCity);
            if(optionalCity != null) {
                cityId = optionalCity.getId();
                model.addAttribute("idCity", cityId);
            }
            else return "redirect:/index?error=" + ManageErrors.STR_ERROR;
        }
        catch (Exception e) {
            logger.error("[CITY CONTROLLER : CITY] : {}", e.getMessage());
            return "redirect:/index?error=" + e.getMessage();
        }
        return "redirect:/index?idCity=" + cityId;
    }

    @GetMapping("/cityForm")
    public String article(Model model) {
        try {
            model.addAttribute("city" , new City());
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            logger.error("[MOVIE CONTROLLER : MANAGE NEW MOVIE] : {} " , e.getMessage());
        }
        return "cityForm";
    }

    @GetMapping("/editCity")
    public String edit(Long idCity, Model model) {
        City city;
        try {
            city = iBusinessImpl.findCityById(idCity);
            model.addAttribute("city", city);
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            logger.error("[MOVIE CONTROLLER : EDIT] : {} " , e.getMessage());
        }
        return "cityForm";
    }

    @PostMapping("/saveCity")
    public String save(@Valid City city, BindingResult bindingResult,
                       @RequestParam(name = "idCity", defaultValue = "0") Long idCity,
                       Model model, RedirectAttributes redirectAttrs) {
        try {
            if(bindingResult.hasErrors()) {
                return "cityForm";
            }
            if (idCity != 0) {
                city.setId(idCity);
                iBusinessImpl.createCity(city);
            }else {
                iBusinessImpl.createCity(city);
            }
        }
        catch(Exception e) {
            redirectAttrs.addAttribute("error",e.getMessage());
            logger.error("[CITY CONTROLLER : SAVE CITY] : {} " , e.getMessage());
        }
        return "redirect:/city";
    }
}
