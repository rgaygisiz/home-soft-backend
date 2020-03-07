package zone.gaygisiz.home.soft.banking.konto.boundary;

import lombok.Data;

@Data
public class Bankkonto {

  private String iban;

  private Bank bank;

  private Person besitzer;

}
