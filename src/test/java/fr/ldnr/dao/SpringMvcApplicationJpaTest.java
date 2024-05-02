package fr.ldnr.dao;

import fr.ldnr.entities.Movie;
import fr.ldnr.entities.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringMvcApplicationJpaTest {

    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    MovieRepository movieRepository;

    @Test
    public void createSession() {

        //GIVEN
        Session session = new Session(new Date(), "11:00", 7, null);

        //WHEN
        sessionRepository.save(session);

        //THEN
        assertTrue(sessionRepository.findById(session.getId()).isPresent());
    }

    @Test
    public void deleteSession() {

        //GIVEN
        Session session = new Session(new Date(), "11:00", 7, null);

        //WHEN
        sessionRepository.save(session);
        sessionRepository.delete(session);

        //THEN
        assertFalse(sessionRepository.findById(session.getId()).isPresent());
    }

    @Test
    public void findSessionByDate() {

        //GIVEN
        Date date = new Date(200, 5, 21);
        Session session = new Session(date, "11:00", 7, null);
        Session session1 = new Session(date, "23:00", 10, null);
        Pageable pageable = (Pageable) PageRequest.of(0,5);

        //WHEN
        sessionRepository.save(session);
        sessionRepository.save(session1);
        Page<Session> sessionPage = sessionRepository.findByDate(date, pageable);

        //THEN
        assertEquals(2, sessionPage.getTotalElements());
        assertEquals(1, sessionPage.getTotalPages());
        assertEquals(date, sessionPage.getContent().get(0).getDate());
        assertEquals(date, sessionPage.getContent().get(1).getDate());
    }

    @Test
    public void findSessionByMovieId() {

        //GIVEN
        Movie movie = new Movie("Seigneur des anneaux", "Fantastique", "300", null, null);
        Session session = new Session(new Date(), "11:00", 7, movie);
        Session session2 = new Session(new Date(), "11:00", 7, movie);
        Pageable pageable = (Pageable) PageRequest.of(0,5);

        //WHEN
        movieRepository.save(movie);
        sessionRepository.save(session);
        sessionRepository.save(session2);
        Page<Session> sessionPage = sessionRepository.findByMovieId(movie.getId(), pageable);

        //THEN
        assertEquals(2, sessionPage.getTotalElements());
        assertEquals(1, sessionPage.getTotalPages());
        assertEquals(movie.getId(), sessionPage.getContent().get(0).getMovie().getId());
        assertEquals(movie.getId(), sessionPage.getContent().get(1).getMovie().getId());
    }
}
