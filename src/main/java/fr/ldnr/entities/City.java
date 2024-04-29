package fr.ldnr.entities;

import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "movieTheaters")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=1,max=20)
    private String name;

    @OneToMany(mappedBy = "city")
    private Collection<MovieTheater> movieTheaters;

    public City(String name, Collection<MovieTheater> movieTheaters) {
        this.name = name;
        this.movieTheaters = movieTheaters;
    }
}
