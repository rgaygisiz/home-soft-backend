package zone.gaygisiz.home.soft.banking.konto;

import lombok.Data;
import zone.gaygisiz.home.soft.banking.bank.Bank;

import javax.persistence.*;

@Data
@Entity
public class Bankverbindung {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String iban;

    @ManyToOne
    private Bank bank;

}
