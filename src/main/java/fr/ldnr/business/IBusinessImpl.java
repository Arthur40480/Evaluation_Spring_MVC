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
import org.springframework.stereotype.Service;

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


    //MOVIE_THEATER
    @Override
    public void createMovieTheater(MovieTheater movieTheater) { movieTheaterRepository.save(movieTheater); }

    @Override
    public Optional<MovieTheater> findMovieTheaterById(Long idMovieTheater) { return movieTheaterRepository.findById(idMovieTheater); }

    @Override
    public List<MovieTheater> findAllMovieTheater() { return movieTheaterRepository.findAll(); }

    @Override
    public List<MovieTheater> findMovieTheaterByCity(Long idCity) { return movieTheaterRepository.findByCityId(idCity); }

    @Override
    public List<MovieTheater> findMovieTheaterByKeyword(String kw) { return movieTheaterRepository.findByNameContains(kw); }


    //MOVIE
    @Override
    public void createMovie(Movie movie) { movieRepository.save(movie); }

    @Override
    public List<Movie> findAllMovie() { return movieRepository.findAll(); }

    @Override
    public List<Movie> findMovieByKeyword(String kw) { return movieRepository.findByNameContains(kw); }

    @Override
    public List<Movie> findMovieByMovieTheater(Long idMovieTheater) { return movieRepository.findByMovieTheaterId(idMovieTheater); }


    //SESSION
    @Override
    public void createSession(Session session) { sessionRepository.save(session); }

    @Override
    public List<Session> findAllSession() { return sessionRepository.findAll(); }
}
