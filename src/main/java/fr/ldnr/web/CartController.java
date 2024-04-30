package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
public class CartController {

    @Autowired
    IBusinessImpl iBusiness;

    private final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cart", iBusiness.getCart());
        return "cart";
    }
}
