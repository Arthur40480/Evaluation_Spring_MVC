package fr.ldnr.dao;

import fr.ldnr.entities.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    public Page<Session> findAll(Pageable pageable);
    public Page<Session> findByMovieId(Long idMovie, Pageable pageable);
    public Page<Session> findByDate(Date date, Pageable pageable);
}
