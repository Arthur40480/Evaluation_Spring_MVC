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
@ToString(exclude = "movies")
public class MovieTheater implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=1,max=50)
    private String name;

    @NotNull
    @Size(min=1,max=50)
    private String address;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "movieTheater")
    private Collection<Movie> movies;

    public MovieTheater(String name, String address, City city, Collection<Movie> movies) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.movies = movies;
    }
}
