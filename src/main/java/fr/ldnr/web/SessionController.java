package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SessionController {

    @Autowired
    IBusinessImpl iBusinessImpl;

    @GetMapping("/session")
    public String session(Model model) {
        List<Session> sessionList = iBusinessImpl.findAllSession();
        model.addAttribute("sessionList", sessionList);
        return "session";
    }
}
