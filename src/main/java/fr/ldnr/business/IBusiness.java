package fr.ldnr.business;

import fr.ldnr.entities.City;
import fr.ldnr.entities.Movie;
import fr.ldnr.entities.MovieTheater;
import fr.ldnr.entities.Session;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public Page<MovieTheater> findMovieTheaterByCity(Long idCity, int page);
    public Page<MovieTheater> findMovieTheaterByKeyword(String kw, int page);
    public void saveMovieTheater(MovieTheater movieTheater);

    //MOVIE
    public void createMovie(Movie movie);
    public List<Movie> findAllMovie();
    public Optional<Movie> findMovieById(Long idMovie);
    public Page<Movie> findMovieByKeyword(String kw, int page);
    public Page<Movie> findMovieByMovieTheater(Long idMovieTheater, int page);

    //SESSION
    public void createSession(Session session);
    public Page<Session> findAllSession(int page);
    public Page<Session> findSessionByMovie(Long idMovie, int page);
    public Page<Session> findSessionByDate(Date date, int page);
}
