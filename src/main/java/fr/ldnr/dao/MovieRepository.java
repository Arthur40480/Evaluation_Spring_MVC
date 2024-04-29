package fr.ldnr.dao;

import fr.ldnr.entities.Movie;
import fr.ldnr.entities.MovieTheater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    public List<Movie> findByNameContains(String kw);
}
