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

    //MOVIE_THEATER
    @Override
    public void createMovieTheater(MovieTheater movieTheater) { movieTheaterRepository.save(movieTheater); }

    //MOVIE
    @Override
    public void createMovie(Movie movie) { movieRepository.save(movie); }

    //SESSION
    @Override
    public void createSession(Session session) { sessionRepository.save(session); }
}
