package fr.ldnr.business;

import fr.ldnr.dao.*;
import fr.ldnr.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class IBusinessImpl implements IBusiness {

    /** Panier contenant les séances */
    private HashMap<Long, Session> cart;

    /** Client */
    private Customer customer;

    /** Références aux repositories */
    @Autowired
    CityRepository cityRepository;
    @Autowired
    MovieTheaterRepository movieTheaterRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    /**
     * Constructeur pour IBusinessImpl.
     * Initialise le panier et définit le client à null.
     */
    public IBusinessImpl() {
        cart = new HashMap<>();
        customer = null;
    }

    // CITY
    /**
     * Crée une ville.
     * @param city La ville à créer.
     */
    @Override
    public void createCity(City city) { cityRepository.save(city); }

    /**
     * Trouve une ville par son ID.
     * @param idCity L'ID de la ville à trouver.
     * @return La ville trouvée, ou null si non trouvée.
     */
    @Override
    public City findCityById(Long idCity) {
        Optional<City> optional = cityRepository.findById(idCity);
        return optional.isPresent()? optional.get() : null;
    }

    /**
     * Trouve toutes les villes.
     * @return La liste de toutes les villes.
     */
    @Override
    public List<City> findAllCity() { return cityRepository.findAll(); }

    /**
     * Trouve les villes par mot-clé.
     * @param kw Le mot-clé à rechercher.
     * @param page Le numéro de page.
     * @return La page de villes correspondante au mot-clé.
     */
    @Override
    public Page<City> findCityByKeyword(String kw, int page) { return cityRepository.findByNameContains(kw, PageRequest.of(page, 2)); }

    /**
     * Supprime une ville.
     * @param city La ville à supprimer.
     */
    @Override
    public void deleteCity(City city) { cityRepository.delete(city); }

    //MOVIE_THEATER
    /**
     * Crée un cinéma.
     * @param movieTheater Le cinéma à créer.
     */
    @Override
    public void createMovieTheater(MovieTheater movieTheater) { movieTheaterRepository.save(movieTheater); }

    /**
     * Trouve un cinéma par son ID.
     * @param idMovieTheater L'ID du cinéma à trouver.
     * @return Le cinéma trouvé, ou null s'il n'existe pas.
     */
    @Override
    public MovieTheater findMovieTheaterById(Long idMovieTheater) {
        Optional<MovieTheater> optional = movieTheaterRepository.findById(idMovieTheater);
        return optional.isPresent()? optional.get() : null;
    }

    /**
     * Trouve tous les cinémas.
     * @return La liste de tous les cinémas.
     */
    @Override
    public List<MovieTheater> findAllMovieTheater() { return movieTheaterRepository.findAll(); }

    /**
     * Trouve les cinémas par ville.
     * @param idCity L'ID de la ville.
     * @param page Le numéro de page.
     * @return La page de cinémas correspondant à la ville.
     */
    @Override
    public Page<MovieTheater> findMovieTheaterByCity(Long idCity, int page) { return movieTheaterRepository.findByCityId(idCity, PageRequest.of(page, 2)); }

    /**
     * Trouve les cinémas par mot-clé.
     * @param kw Le mot-clé à rechercher.
     * @param page Le numéro de page.
     * @return La page de cinémas correspondant au mot-clé.
     */
    @Override
    public Page<MovieTheater> findMovieTheaterByKeyword(String kw, int page) { return movieTheaterRepository.findByNameContains(kw, PageRequest.of(page, 2)); }

    /**
     * Supprime un cinéma.
     * @param movieTheater Le cinéma à supprimer.
     */
    @Override
    public void deleteMovieTheater(MovieTheater movieTheater) { movieTheaterRepository.delete(movieTheater); }

    //MOVIE
    /**
     * Crée un film.
     * @param movie Le film à créer.
     */
    @Override
    public void createMovie(Movie movie) { movieRepository.save(movie); }

    /**
     * Trouve un film par son ID.
     * @param idMovie L'ID du film à trouver.
     * @return Le film trouvé, ou null s'il n'existe pas.
     */
    @Override
    public Movie findMovieById(Long idMovie) {
        Optional<Movie> optional = movieRepository.findById(idMovie);
        return optional.isPresent()? optional.get() : null;
    }

    /**
     * Trouve tous les films.
     * @return La liste de tous les films.
     */
    @Override
    public List<Movie> findAllMovie() { return movieRepository.findAll(); }

    /**
     * Trouve les films par mot-clé.
     * @param kw Le mot-clé à rechercher.
     * @param page Le numéro de page.
     * @return La page de films correspondant au mot-clé.
     */
    @Override
    public Page<Movie> findMovieByKeyword(String kw, int page) { return movieRepository.findByNameContains(kw, PageRequest.of(page, 2)); }

    /**
     * Trouve les films par cinéma.
     * @param idMovieTheater L'ID du cinéma.
     * @param page Le numéro de page.
     * @return La page de films correspondant au cinéma.
     */
    @Override
    public Page<Movie> findMovieByMovieTheater(Long idMovieTheater, int page) { return movieRepository.findByMovieTheaterId(idMovieTheater, PageRequest.of(page, 2)); }

    /**
     * Supprime un film.
     * @param movie Le film à supprimer.
     */
    @Override
    public void deleteMovie(Movie movie) { movieRepository.delete(movie); }

    //SESSION
    /**
     * Crée une séance.
     * @param session La séance à créer.
     */
    @Override
    public void createSession(Session session) { sessionRepository.save(session); }

    /**
     * Trouve une séance par son ID.
     * @param idSession L'ID de la séance à trouver.
     * @return La séance trouvée, ou null si elle n'existe pas.
     */
    @Override
    public Session findSessionById(Long idSession) {
        Optional<Session> optional = sessionRepository.findById(idSession);
        return optional.isPresent()? optional.get() : null;
    }

    /**
     * Trouve toutes les séances.
     * @param page Le numéro de page.
     * @return La page de séances.
     */
    @Override
    public Page<Session> findAllSession(int page) { return sessionRepository.findAll(PageRequest.of(page, 2)); }

    /**
     * Trouve les séances d'un film.
     * @param idMovie L'ID du film.
     * @param page Le numéro de page.
     * @return La page de séances correspondant au film.
     */
    @Override
    public Page<Session> findSessionByMovie(Long idMovie, int page) { return sessionRepository.findByMovieId(idMovie, PageRequest.of(page, 2)); }

    /**
     * Trouve les séances par date.
     * @param date La date des séances.
     * @param page Le numéro de page.
     * @return La page de séances correspondant à la date.
     */
    @Override
    public Page<Session> findSessionByDate(Date date, int page) { return sessionRepository.findByDate(date, PageRequest.of(page, 2)); }

    /**
     * Supprime une séance.
     * @param session La séance à supprimer.
     */
    @Override
    public void deleteSession(Session session) { sessionRepository.delete(session); }

    //CART
    /**
     * Obtient le panier.
     * @return Le panier.
     */
    @Override
    public HashMap<Long, Session> getCart() { return this.cart; }

    /**
     * Ajoute une séance au panier.
     * @param session La séance à ajouter.
     */
    @Override
    public void addToCart(Session session) {
        Session s = cart.get(session.getId());
        if(s != null) {
            s.setQuantity(s.getQuantity() + 1);
        }
        else cart.put(session.getId(), session);
    }

    /**
     * Supprime une séance du panier.
     * @param idSession L'ID de la séance à supprimer.
     */
    @Override
    public void removeToCart(Long idSession) { cart.remove(idSession); }

    /**
     * Vide le panier.
     */
    @Override
    public void clearCart() { cart.clear(); }

    /**
     * Calcule le montant total du panier.
     * @return Le montant total du panier.
     */
    @Override
    public double getTotalAmount() {
        double total = 0;
        for(Session session : cart.values()) {
            total += session.getPrice() * session.getQuantity();
        }
        return total;
    }

    //CUSTOMER
    /**
     * Définit le client.
     * @param customer Le client à définir.
     */
    public void setCustomer(Customer customer) { this.customer = customer; }

    /**
     * Obtient le client.
     * @return Le client.
     */
    public Customer getCustomer() { return this.customer; }

    /**
     * Crée un client.
     * @param customer Le client à créer.
     */
    @Override
    public void createCustomer(Customer customer) { customerRepository.save(customer); }

    //ORDER
    /**
     * Crée une commande.
     * @param order La commande à créer.
     */
    @Override
    public void createOrder(Order order) { orderRepository.save(order); }

    //ORDER_ITEM
    /**
     * Crée un élément de commande.
     * @param orderItem L'élément de commande à créer.
     */
    @Override
    public void createOrderItem(OrderItem orderItem) { orderItemRepository.save(orderItem); }
}
