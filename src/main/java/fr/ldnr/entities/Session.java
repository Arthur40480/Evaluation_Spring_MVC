package fr.ldnr.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
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

    @DecimalMin("1")
    private double price;

    @ManyToOne
    private Movie movie;

    @JsonProperty("quantity")
    private int quantity = 1;

    public Session(Date date, String time, double price, Movie movie) {
        this.date = date;
        this.time = time;
        this.price = price;
        this.movie = movie;
    }

    public Session(Long id, Date date, String time, double price, Movie movie) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.price = price;
        this.movie = movie;
    }

}
