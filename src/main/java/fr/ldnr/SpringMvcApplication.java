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

import java.sql.Time;
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

		MovieTheater cgr = new MovieTheater("CGR de Paris", "10 rue des tulipes", paris, movieList);
		MovieTheater leGrandRex = new MovieTheater("Le Grand Rex", "20 impasse des acteurs", marseille, movieList);
		MovieTheater leBrady = new MovieTheater("Le Brady", "5 rue des mimosas", bordeaux, movieList);
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

		Session mondaySession1 = new Session(new Date(124, 5, 21), Time.valueOf("11:00:00"), lor);
		Session mondaySession2 = new Session(new Date(124, 5, 21), Time.valueOf("15:00:00"), interstellar);
		Session mondaySession3 = new Session(new Date(124, 5, 21), Time.valueOf("21:30:00"), inception);
		Session tuesdaySession1 = new Session(new Date(124, 5, 22), Time.valueOf("23:00:00"), lesEvades);
		Session tuesdaySession2 = new Session(new Date(124, 5, 22), Time.valueOf("09:15:00"), laLaLand);
		Session tuesdaySession3 = new Session(new Date(124, 5, 22), Time.valueOf("12:00:00"), leParrain);
		Session wednesdaySession1 = new Session(new Date(124, 5, 23), Time.valueOf("16:00:00"), forestGump);
		Session wednesdaySession2 = new Session(new Date(124, 5, 23), Time.valueOf("12:30:00"), pulpFiction);
		Session wednesdaySession3 = new Session(new Date(124, 5, 23), Time.valueOf("10:45:00"), titanic);
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
