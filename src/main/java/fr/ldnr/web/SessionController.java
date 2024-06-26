package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.City;
import fr.ldnr.entities.Movie;
import fr.ldnr.entities.MovieTheater;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class SessionController {

    @Autowired
    IBusinessImpl iBusinessImpl;

    private final Logger logger = LoggerFactory.getLogger(SessionController.class);


    @GetMapping("/session")
    public String session(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "idMovie", defaultValue = "0") Long idMovie,
                          @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        Page<Session> sessionList = null;

        if (idMovie != 0) {
            Movie optionalMovie = iBusinessImpl.findMovieById(idMovie);
            if (optionalMovie == null) {
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
        model.addAttribute("sessionList", sessionList.getContent());
        model.addAttribute("nbcart", iBusinessImpl.getCart().size());
        return "session";
    }

    @GetMapping("/sessionForm")
    public String article(Model model) {
        model.addAttribute("session" , new Session());
        try {
            model.addAttribute("movieList", iBusinessImpl.findAllMovie());
            model.addAttribute("nbcart", iBusinessImpl.getCart().size());
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            logger.error("[SESSION CONTROLLER : MANAGE NEW SESSION] : {} " , e.getMessage());
        }
        return "sessionForm";
    }

    @GetMapping("/editSession")
    public String edit(Long idSession, Model model) {
        Session session;
        try {
            session = iBusinessImpl.findSessionById(idSession);
            model.addAttribute("movieList", iBusinessImpl.findAllMovie());
            model.addAttribute("session", session);
            model.addAttribute("idSession", idSession);
            model.addAttribute("nbcart", iBusinessImpl.getCart().size());
        }
        catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            logger.error("[SESSION CONTROLLER : EDIT] : {} " , e.getMessage());
        }
        return "sessionForm";
    }

    @PostMapping("/saveSession")
    public String save(@Valid Session session, BindingResult bindingResult,
                       @RequestParam(name = "idSession", defaultValue = "0") Long idSession,
                       Model model, RedirectAttributes redirectAttrs) {
        try {
            if(bindingResult.hasErrors()) {
                model.addAttribute("movieList", iBusinessImpl.findAllMovie());
                return "sessionForm";
            }
            if(idSession != 0) {
                session.setId(idSession);
                iBusinessImpl.createSession(session);
            } else {
                iBusinessImpl.createSession(session);
            }
        }
        catch(Exception e) {
            redirectAttrs.addAttribute("error",e.getMessage());
            logger.error("[SESSION CONTROLLER : SAVE SESSION] : {} " , e.getMessage());
        }
        return "redirect:/session";
    }

    @GetMapping("/deleteSession")
    public String deleteCity(Long idSession, Model model, RedirectAttributes redirectAttributes) {
        try {
            Session sessionToDelete = iBusinessImpl.findSessionById(idSession);
            if (sessionToDelete != null) {
                iBusinessImpl.deleteSession(sessionToDelete);
                model.addAttribute("success", "SUPPRESSION REUSSIE ");
            }else {
                model.addAttribute("error", "SUPPRESSION IMPOSSIBLE : La séance n'éxiste pas");
            }
            Page<Session> sessionList = iBusinessImpl.findAllSession(0);
            model.addAttribute("sessionList", sessionList);
            model.addAttribute("currentPage", 0);
            model.addAttribute("pages", new int[sessionList.getTotalPages()]);
            model.addAttribute("nbcart", iBusinessImpl.getCart().size());

        } catch (Exception e) {
            redirectAttributes.addAttribute("error",e.getMessage());
            logger.error("[SESSION CONTROLLER : DELETE] : {} " , e.getMessage());
        }
        return "redirect:/session";
    }
}