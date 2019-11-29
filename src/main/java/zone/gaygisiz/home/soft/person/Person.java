package zone.gaygisiz.home.soft.person;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(columnDefinition = "DATE")
    private LocalDate birthday;

    private String birthplace;
}
