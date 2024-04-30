package fr.ldnr;

import fr.ldnr.dao.CityRepository;
import fr.ldnr.dao.MovieRepository;
import fr.ldnr.dao.MovieTheaterRepository;
import fr.ldnr.dao.SessionRepository;
import fr.ldnr.entities.City;
import fr.ldnr.entities.Movie;
import fr.ldnr.entities.MovieTheater;
import fr.ldnr.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringMvcApplication implements CommandLineRunner {

	@Autowired
	CityRepository cityRepository;
	@Autowired
	MovieTheaterRepository movieTheaterRepository;
	@Autowired
	MovieRepository movieRepository;
	@Autowired
	SessionRepository sessionRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcApplication.class, args);
	}

	public void run(String... args) throws Exception {
		generateDatas();
	}

	private void generateDatas() {
		List<MovieTheater> movieTheaterList = null;
		List<Movie> movieList = null;
		List<Session> sessionList = null;

		City paris = new City("Paris",movieTheaterList);
		City marseille = new City("Marseille",movieTheaterList);
		City bordeaux = new City("Bordeaux",movieTheaterList);
		cityRepository.save(paris);
		cityRepository.save(marseille);
		cityRepository.save(bordeaux);

		MovieTheater paradisio = new MovieTheater("Le Paradisio", "13 place des pigeon", paris, movieList);
		MovieTheater cinepolis = new MovieTheater("Cinépolis", "22 avenue des rosiers", marseille, movieList);
		MovieTheater cinestar = new MovieTheater("Cinéstar", "9 rue des artisans", bordeaux, movieList);
		MovieTheater cinecity = new MovieTheater("CinéCity", "45 rue des éléphants", paris, movieList);
		MovieTheater cinemaMajestic = new MovieTheater("Le Majestic", "place des magiciens", marseille, movieList);
		MovieTheater cineplex = new MovieTheater("Cinéplex", "5 rue des mimosas", bordeaux, movieList);
		MovieTheater apollo = new MovieTheater("L'Apollo", "24 routes des cerisiers", paris, movieList);
		MovieTheater vogue = new MovieTheater("Le CinéVogue", "2 impasse des voitures", marseille, movieList);
		MovieTheater etoile = new MovieTheater("L'Etoilé'", "5 rue des nains", bordeaux, movieList);
		MovieTheater cgr = new MovieTheater("CGR de Paris", "10 rue des tulipes", paris, movieList);
		MovieTheater leGrandRex = new MovieTheater("Le Grand Rex", "20 impasse des acteurs", marseille, movieList);
		MovieTheater leBrady = new MovieTheater("Le Brady", "5 rue des mimosas", bordeaux, movieList);
		movieTheaterRepository.save(paradisio);
		movieTheaterRepository.save(cinepolis);
		movieTheaterRepository.save(cinestar);
		movieTheaterRepository.save(cinecity);
		movieTheaterRepository.save(cinemaMajestic);
		movieTheaterRepository.save(cineplex);
		movieTheaterRepository.save(apollo);
		movieTheaterRepository.save(vogue);
		movieTheaterRepository.save(etoile);
		movieTheaterRepository.save(cgr);
		movieTheaterRepository.save(leGrandRex);
		movieTheaterRepository.save(leBrady);

		Movie lor = new Movie("Seigneur des anneaux", "Fantastique", "300", cgr, sessionList);
		Movie inception = new Movie("Inception", "Science-fiction, Thriller", "210", cgr, sessionList);
		Movie laLaLand = new Movie("La La Land", "Comédie musqicale, Romance", "105", cgr, sessionList);
		Movie leParrain = new Movie("Le Parrain", "Drame, Crime", "120", leGrandRex, sessionList);
		Movie interstellar = new Movie("Interstellar", "Science-fiction", "145", leGrandRex, sessionList);
		Movie forestGump = new Movie("Forest Gump", "Comédie dramatique", "105", leGrandRex, sessionList);
		Movie pulpFiction = new Movie("Pulp Fiction", "Drame, Crime", "160", leGrandRex, sessionList);
		Movie titanic = new Movie("Titanic", "Drame, Romance", "135", leBrady, sessionList);
		Movie lesEvades = new Movie("Les Evadés", "Drame, Crime", "300", leBrady, sessionList);

		movieRepository.save(lor);
		movieRepository.save(inception);
		movieRepository.save(laLaLand);
		movieRepository.save(leParrain);
		movieRepository.save(interstellar);
		movieRepository.save(forestGump);
		movieRepository.save(pulpFiction);
		movieRepository.save(titanic);
		movieRepository.save(lesEvades);

		Session mondaySession1 = new Session(new Date(124, 5, 21), "11:00", lor);
		Session mondaySession2 = new Session(new Date(124, 5, 21), "17:00", interstellar);
		Session mondaySession3 = new Session(new Date(124, 5, 21), "20:30", inception);
		Session tuesdaySession1 = new Session(new Date(124, 5, 22), "22:15", lesEvades);
		Session tuesdaySession2 = new Session(new Date(124, 5, 22), "00:00", laLaLand);
		Session tuesdaySession3 = new Session(new Date(124, 5, 22), "09:45", leParrain);
		Session wednesdaySession1 = new Session(new Date(124, 5, 23), "11:35", forestGump);
		Session wednesdaySession2 = new Session(new Date(124, 5, 23), "18:25", pulpFiction);
		Session wednesdaySession3 = new Session(new Date(124, 5, 23), "19:00", titanic);
		sessionRepository.save(mondaySession1);
		sessionRepository.save(mondaySession2);
		sessionRepository.save(mondaySession3);
		sessionRepository.save(tuesdaySession1);
		sessionRepository.save(tuesdaySession2);
		sessionRepository.save(tuesdaySession3);
		sessionRepository.save(wednesdaySession1);
		sessionRepository.save(wednesdaySession2);
		sessionRepository.save(wednesdaySession3);
	}
}
