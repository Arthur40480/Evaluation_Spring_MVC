package fr.ldnr.dao;

import fr.ldnr.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    public List<Session> findByMovieId(Long idMovie);
    public List<Session> findByDate(Date date);
}
