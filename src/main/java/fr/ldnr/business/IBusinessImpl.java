package fr.ldnr.business;

import fr.ldnr.dao.CityRepository;
import fr.ldnr.dao.MovieRepository;
import fr.ldnr.dao.MovieTheaterRepository;
import fr.ldnr.dao.SessionRepository;
import fr.ldnr.entities.City;
import fr.ldnr.entities.Movie;
import fr.ldnr.entities.MovieTheater;
import fr.ldnr.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IBusinessImpl implements IBusiness {

    @Autowired
    CityRepository cityRepository;
    @Autowired
    MovieTheaterRepository movieTheaterRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    SessionRepository sessionRepository;

    //CITY
    @Override
    public void createCity(City city) { cityRepository.save(city); }

    @Override
    public Optional<City> findCityById(Long idCity) { return cityRepository.findById(idCity); }

    @Override
    public List<City> findAllCity() { return cityRepository.findAll(); }

    @Override
    public Page<City> findCityByKeyword(String kw, int page) { return cityRepository.findByNameContains(kw, PageRequest.of(page, 2)); }


    //MOVIE_THEATER
    @Override
    public void createMovieTheater(MovieTheater movieTheater) { movieTheaterRepository.save(movieTheater); }

    @Override
    public MovieTheater findMovieTheaterById(Long idMovieTheater) {
        Optional<MovieTheater> optional = movieTheaterRepository.findById(idMovieTheater);
        return optional.isPresent()? optional.get() : null;
    }

    @Override
    public List<MovieTheater> findAllMovieTheater() { return movieTheaterRepository.findAll(); }

    @Override
    public Page<MovieTheater> findMovieTheaterByCity(Long idCity, int page) { return movieTheaterRepository.findByCityId(idCity, PageRequest.of(page, 2)); }

    @Override
    public Page<MovieTheater> findMovieTheaterByKeyword(String kw, int page) { return movieTheaterRepository.findByNameContains(kw, PageRequest.of(page, 2)); }


    //MOVIE
    @Override
    public void createMovie(Movie movie) { movieRepository.save(movie); }

    @Override
    public Movie findMovieById(Long idMovie) {
        Optional<Movie> optional = movieRepository.findById(idMovie);
        return optional.isPresent()? optional.get() : null;
    }

    @Override
    public List<Movie> findAllMovie() { return movieRepository.findAll(); }

    @Override
    public Page<Movie> findMovieByKeyword(String kw, int page) { return movieRepository.findByNameContains(kw, PageRequest.of(page, 2)); }

    @Override
    public Page<Movie> findMovieByMovieTheater(Long idMovieTheater, int page) { return movieRepository.findByMovieTheaterId(idMovieTheater, PageRequest.of(page, 2)); }


    //SESSION
    @Override
    public void createSession(Session session) { sessionRepository.save(session); }

    @Override
    public Session findSessionById(Long idSession) {
        Optional<Session> optional = sessionRepository.findById(idSession);
        return optional.isPresent()? optional.get() : null;
    }

    @Override
    public Page<Session> findAllSession(int page) { return sessionRepository.findAll(PageRequest.of(page, 2)); }

    @Override
    public Page<Session> findSessionByMovie(Long idMovie, int page) { return sessionRepository.findByMovieId(idMovie, PageRequest.of(page, 2)); }

    @Override
    public Page<Session> findSessionByDate(Date date, int page) { return sessionRepository.findByDate(date, PageRequest.of(page, 2)); }
}
