package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Movie;
import fr.ldnr.entities.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Controller
public class SessionController {

    @Autowired
    IBusinessImpl iBusinessImpl;

    private final Logger logger = LoggerFactory.getLogger(SessionController.class);


    @GetMapping("/session")
    public String session(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "idMovie", defaultValue = "0") Long idMovie,
                          @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                          @ModelAttribute(name = "error") String error) {
        Page<Session> sessionList = null;

        if (idMovie != 0) {
            Optional<Movie> optionalMovie = iBusinessImpl.findMovieById(idMovie);
            if (!optionalMovie.isPresent()) {
                model.addAttribute("error", "ID MOVIE INVALID");
            } else {
                sessionList = iBusinessImpl.findSessionByMovie(idMovie, page);
            }
        } else {
            if (date != null) {
                sessionList = iBusinessImpl.findSessionByDate(date, page);
            } else {
                sessionList = iBusinessImpl.findAllSession(page);
            }
        }

        model.addAttribute("date", date);
        model.addAttribute("idMovie", idMovie);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", new int[sessionList.getTotalPages()]);
        model.addAttribute("error", error);
        model.addAttribute("sessionList", sessionList.getContent());
        return "session";
    }

    @GetMapping("/sessionForm")
    public String article(Model model) {
        model.addAttribute("session" , new Session());
        try {
            model.addAttribute("movieList", iBusinessImpl.findAllMovie());
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            logger.error("[SESSION CONTROLLER : MANAGE NEW SESSION] : {} " , e.getMessage());
        }
        return "sessionForm";
    }

    @PostMapping("/saveSession")
    public String save(@Valid Session session, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
        try {
            if(bindingResult.hasErrors()) {
                model.addAttribute("movieList", iBusinessImpl.findAllMovie());
                return "sessionForm";
            }
            System.out.println(session);
            iBusinessImpl.createSession(session);
        }
        catch(Exception e) {
            redirectAttrs.addAttribute("error",e.getMessage());
            logger.error("[SESSION CONTROLLER : SAVE SESSION] : {} " , e.getMessage());
        }
        return "redirect:/session";
    }
}