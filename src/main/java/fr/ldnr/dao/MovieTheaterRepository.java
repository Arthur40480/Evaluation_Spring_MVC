package fr.ldnr.dao;

import fr.ldnr.entities.MovieTheater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieTheaterRepository extends JpaRepository<MovieTheater, Long> {
    public List<MovieTheater> findByCityId(Long idCity);
}
