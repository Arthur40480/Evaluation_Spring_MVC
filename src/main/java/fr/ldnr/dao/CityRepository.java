package fr.ldnr.dao;

import fr.ldnr.entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    public Page<City> findByNameContains(String kw, Pageable pageable);
}
