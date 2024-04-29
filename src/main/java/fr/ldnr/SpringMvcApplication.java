package fr.ldnr;

import fr.ldnr.entities.City;
import fr.ldnr.entities.Movie;
import fr.ldnr.entities.MovieTheater;
import fr.ldnr.entities.Session;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringMvcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcApplication.class, args);
	}

	public void run(String... args) throws Exception {

	}

	private void generateDatas() {
		List<MovieTheater> movieTheaterList = null;
		List<Movie> movieList = null;
		List<Session> sessionList = null;

		City paris = new City("Paris",movieTheaterList);
		City marseille = new City("Marseille",movieTheaterList);
		City bordeaux = new City("Bordeaux",movieTheaterList);

		MovieTheater cgr = new MovieTheater("CGR de Paris", "10 rue des tulipes", movieList);
		MovieTheater leGrandRex = new MovieTheater("Le Grand Rex", "20 impasse des acteurs", movieList);
		MovieTheater leBrady = new MovieTheater("Le Brady", "5 rue des mimosas", movieList);

		Movie lor = new Movie("Seigneur des anneaux", "Fantastique", "300", cgr, sessionList);
		Movie inception = new Movie("Inception", "Science-fiction, Thriller", "210", cgr, sessionList);
		Movie laLaLand = new Movie("La La Land", "Comédie musqicale, Romance", "105", cgr, sessionList);
		Movie leParrain = new Movie("Le Parrain", "Drame, Crime", "120", leGrandRex, sessionList);
		Movie interstellar = new Movie("Interstellar", "Science-fiction", "145", leGrandRex, sessionList);
		Movie forestGump = new Movie("Forest Gump", "Comédie dramatique", "105", leGrandRex, sessionList);
		Movie pulpFiction = new Movie("Pulp Fiction", "Drame, Crime", "160", leGrandRex, sessionList);
		Movie titanic = new Movie("Titanic", "Drame, Romance", "135", leBrady, sessionList);
		Movie lesEvades = new Movie("Les Evadés", "Drame, Crime", "300", leBrady, sessionList);

		Session mondaySession1 = new Session(new Date(2024, 5, 21), 11, lor);
		Session mondaySession2 = new Session(new Date(2024, 5, 21), 15, interstellar);
		Session mondaySession3 = new Session(new Date(2024, 5, 21), 17, inception);
		Session tuesdaySession1 = new Session(new Date(2024, 5, 22), 10, lesEvades);
		Session tuesdaySession2 = new Session(new Date(2024, 5, 22), 14, laLaLand);
		Session tuesdaySession3 = new Session(new Date(2024, 5, 22), 19, leParrain);
		Session wednesdaySession1 = new Session(new Date(2024, 5, 23), 11, forestGump);
		Session wednesdaySession2 = new Session(new Date(2024, 5, 23), 15, pulpFiction);
		Session wednesdaySession3 = new Session(new Date(2024, 5, 23), 17, titanic);
	}
}
