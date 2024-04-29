package fr.ldnr.dao;

import fr.ldnr.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    public List<Movie> findByMovieTheaterId(Long idMovieTheater);
    public List<Movie> findByNameContains(String kw);
}
