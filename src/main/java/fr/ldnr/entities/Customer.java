package fr.ldnr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=5,max=30)
    private String name;

    @NotNull
    @Size(min=5,max=30)
    private String sname;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Size(min=5,max=50)
    private String address;

    @NotNull
    @Pattern(regexp="\\d{10}", message="Numéro de téléphone incorrect")
    private String phone;

    @OneToMany(mappedBy = "customer")
    Collection<Order> orders;
}
