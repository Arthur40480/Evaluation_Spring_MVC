package fr.ldnr.dao;

import fr.ldnr.entities.MovieTheater;
import fr.ldnr.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
