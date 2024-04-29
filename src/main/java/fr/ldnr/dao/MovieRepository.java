package fr.ldnr.dao;

import fr.ldnr.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    public Page<Movie> findByMovieTheaterId(Long idMovieTheater, Pageable pageable);
    public Page<Movie> findByNameContains(String kw, Pageable pageable);
}
