package zone.gaygisiz.home.soft.banking.bank;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String bic;

    @Column(unique = true)
    private String blz;
}
