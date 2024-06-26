package fr.ldnr.business;

import fr.ldnr.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public interface IBusiness {

    //CITY
    public void createCity(City city);
    public City findCityById(Long idCity);
    public List<City> findAllCity();
    public Page<City> findCityByKeyword(String kw, int page);
    public void deleteCity(City city);

    //MOVIE_THEATER
    public void createMovieTheater(MovieTheater movieTheater);
    public MovieTheater findMovieTheaterById(Long idMovieTheater);
    public List<MovieTheater> findAllMovieTheater();
    public Page<MovieTheater> findMovieTheaterByCity(Long idCity, int page);
    public Page<MovieTheater> findMovieTheaterByKeyword(String kw, int page);
    public void deleteMovieTheater(MovieTheater movieTheater);

    //MOVIE
    public void createMovie(Movie movie);
    public List<Movie> findAllMovie();
    public Movie findMovieById(Long idMovie);
    public Page<Movie> findMovieByKeyword(String kw, int page);
    public Page<Movie> findMovieByMovieTheater(Long idMovieTheater, int page);
    public void deleteMovie(Movie move);

    //SESSION
    public void createSession(Session session);
    public Session findSessionById(Long idSession);
    public Page<Session> findAllSession(int page);
    public Page<Session> findSessionByMovie(Long idMovie, int page);
    public Page<Session> findSessionByDate(Date date, int page);
    public void deleteSession(Session session);

    //CART
    public Map<Long, Session> getCart();
    public void addToCart(Session session);
    public void removeToCart(Long idSession);
    public void clearCart();
    public double getTotalAmount();

    //CUSTOMER
    public void createCustomer(Customer customer);

    //ORDER
    public void createOrder(Order order);

    //ORDER_ITEM
    public void createOrderItem(OrderItem orderItem);
}
