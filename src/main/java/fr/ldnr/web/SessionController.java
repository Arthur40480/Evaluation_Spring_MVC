package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Movie;
import fr.ldnr.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class SessionController {

    @Autowired
    IBusinessImpl iBusinessImpl;

    @GetMapping("/session")
    public String session(Model model,
                          @RequestParam(name = "idMovie", defaultValue = "0") Long idMovie,
                          @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                          @ModelAttribute(name="error") String error) {

        List<Session> sessionList = null;

        if (idMovie != 0) {
            Optional<Movie> optionalMovie = iBusinessImpl.findMovieById(idMovie);
            if (!optionalMovie.isPresent()) {
                model.addAttribute("error", "ID MOVIE INVALID");
            } else {
                sessionList = iBusinessImpl.findSessionByMovie(idMovie);
            }
        } else if (date != null) {
            sessionList = iBusinessImpl.findSessionByDate(date);
        } else {
            sessionList = iBusinessImpl.findAllSession();
        }

        model.addAttribute("error", model.getAttribute("error"));
        model.addAttribute("sessionList", sessionList);

        return "session";
    }
}