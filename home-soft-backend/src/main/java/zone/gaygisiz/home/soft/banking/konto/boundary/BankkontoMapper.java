package zone.gaygisiz.home.soft.banking.konto.boundary;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import zone.gaygisiz.home.soft.banking.bank.entity.Bank;
import zone.gaygisiz.home.soft.banking.konto.entity.Bankkonto;
import zone.gaygisiz.home.soft.person.entity.Person;

@Mapper(componentModel = "spring")
public interface BankkontoMapper {

  @Mapping(source = "bank.bic", target = "bic")
  @Mapping(source = "besitzer.id", target = "partnerId")
  Bankkonto boundaryToController(zone.gaygisiz.home.soft.banking.konto.boundary.Bankkonto bankkonto);

  @Mapping(source = "bic", target = "bank.bic")
  @Mapping(source = "partnerId", target = "besitzer.id")
  zone.gaygisiz.home.soft.banking.konto.boundary.Bankkonto controllerToView(Bankkonto bankkonto);


  @Mapping(source = "name", target = "bank.name")
  void mapBankToBankkonto(Bank bank, @MappingTarget zone.gaygisiz.home.soft.banking.konto.boundary.Bankkonto bankkonto);

  @Mapping(source = "firstName", target = "besitzer.firstName")
  @Mapping(source = "lastName", target = "besitzer.lastName")
  void mapPersonToBankkonto(Person person, @MappingTarget zone.gaygisiz.home.soft.banking.konto.boundary.Bankkonto bankkonto);
}
