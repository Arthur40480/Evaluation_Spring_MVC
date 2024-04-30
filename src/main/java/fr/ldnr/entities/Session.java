package fr.ldnr.entities;

import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Session implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "Entrez une heure valide au format HH:mm")
    private String time;

    @ManyToOne
    private Movie movie;

    public Session(Date date, String time, Movie movie) {
        this.date = date;
        this.time = time;
        this.movie = movie;
    }
}
