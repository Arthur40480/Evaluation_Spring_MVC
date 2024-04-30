package fr.ldnr.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.City;
import fr.ldnr.exceptions.ManageErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            Optional<City> optionalCity = iBusinessImpl.findCityById(idCity);
            if(optionalCity.isPresent()) {
                City city = optionalCity.get();
                cityId = city.getId();
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
}
