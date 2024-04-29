package fr.ldnr.dao;

import fr.ldnr.entities.MovieTheater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTheaterRepository extends JpaRepository<MovieTheater, Long> {
    public Page<MovieTheater> findByCityId(Long idCity, Pageable pageable);
    public Page<MovieTheater> findByNameContains(String kw, Pageable pageable);
}
