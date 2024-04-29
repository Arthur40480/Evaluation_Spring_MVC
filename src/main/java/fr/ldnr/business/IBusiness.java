package fr.ldnr.business;

import fr.ldnr.entities.City;
import fr.ldnr.entities.Movie;
import fr.ldnr.entities.MovieTheater;
import fr.ldnr.entities.Session;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IBusiness {
    //CITY
    public void createCity(City city);
    public Optional<City> findCityById(Long idCity);
    public List<City> findAllCity();

    //MOVIE_THEATER
    public void createMovieTheater(MovieTheater movieTheater);
    public Optional<MovieTheater>  findMovieTheaterById(Long idMovieTheater);
    public List<MovieTheater> findAllMovieTheater();
    public List<MovieTheater> findMovieTheaterByCity(Long idCity);
    public List<MovieTheater> findMovieTheaterByKeyword(String kw);

    //MOVIE
    public void createMovie(Movie movie);
    public List<Movie> findAllMovie();

    //SESSION
    public void createSession(Session session);
    public List<Session> findAllSession();
}
