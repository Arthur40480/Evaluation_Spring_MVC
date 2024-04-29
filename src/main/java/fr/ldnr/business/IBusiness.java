package fr.ldnr.business;

import fr.ldnr.entities.City;
import fr.ldnr.entities.Movie;
import fr.ldnr.entities.MovieTheater;
import fr.ldnr.entities.Session;
import org.springframework.stereotype.Service;

@Service
public interface IBusiness {
    //CITY
    public void createCity(City city);

    //MOVIE_THEATER
    public void createMovieTheater(MovieTheater movieTheater);

    //MOVIE
    public void createMovie(Movie movie);

    //SESSION
    public void createSession(Session session);
}
