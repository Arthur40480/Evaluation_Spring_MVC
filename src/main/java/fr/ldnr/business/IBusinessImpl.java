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
    public List<City> findAllCity() { return cityRepository.findAll(); }

    //MOVIE_THEATER
    @Override
    public void createMovieTheater(MovieTheater movieTheater) { movieTheaterRepository.save(movieTheater); }

    @Override
    public List<MovieTheater> findAllMovieTheater() { return movieTheaterRepository.findAll(); }

    @Override
    public List<MovieTheater> findMovieTheaterByCity(Long idCity) { return movieTheaterRepository.findByCityId(idCity); }

    //MOVIE
    @Override
    public void createMovie(Movie movie) { movieRepository.save(movie); }

    //SESSION
    @Override
    public void createSession(Session session) { sessionRepository.save(session); }
}
