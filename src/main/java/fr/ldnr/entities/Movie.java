package fr.ldnr.entities;

import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=1,max=50)
    private String name;

    @NotNull
    @Size(min=1,max=50)
    private String genre;

    @DecimalMin("50")
    private String duration;

    @ManyToOne
    private MovieTheater movieTheater;

    @OneToMany(mappedBy = "movie")
    private Collection<Session> session;

    public Movie(String name, String genre, String duration, MovieTheater movieTheater, Collection<Session> session) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.movieTheater = movieTheater;
        this.session = session;
    }
}
