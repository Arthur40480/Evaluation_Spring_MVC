package fr.ldnr.business;

import fr.ldnr.entities.City;
import fr.ldnr.entities.Movie;
import fr.ldnr.entities.MovieTheater;
import fr.ldnr.entities.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBusiness {
    //CITY
    public void createCity(City city);
    public List<City> findAllCity();

    //MOVIE_THEATER
    public void createMovieTheater(MovieTheater movieTheater);
    public List<MovieTheater> findAllMovieTheater();
    public List<MovieTheater> findMovieTheaterByCity(Long idCity);

    //MOVIE
    public void createMovie(Movie movie);

    //SESSION
    public void createSession(Session session);
}
