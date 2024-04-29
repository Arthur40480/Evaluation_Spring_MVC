package fr.ldnr.entities;

import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private Date date;

    @NotNull
    private Time time;

    @ManyToOne
    private Movie movie;

    public Session(Date date, Time time, Movie movie) {
        this.date = date;
        this.time = time;
        this.movie = movie;
    }
}
