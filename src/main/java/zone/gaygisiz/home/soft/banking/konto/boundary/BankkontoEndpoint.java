package zone.gaygisiz.home.soft.banking.konto.boundary;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zone.gaygisiz.home.soft.banking.bank.controller.BankManager;
import zone.gaygisiz.home.soft.banking.konto.controller.BankkontenManager;
import zone.gaygisiz.home.soft.person.controller.PersonManager;
import zone.gaygisiz.home.soft.web.ApplicationResponse;

@RestController()
@RequestMapping(path = "/api/banken/konten")
public class BankkontoEndpoint {

  @Autowired
  private BankkontenManager bankkontenManager;

  @Autowired
  private BankkontoMapper bankkontoMapper;

  @Autowired
  private BankManager bankManager;

  @Autowired
  private PersonManager personManager;

  @PostMapping
  public ResponseEntity<ApplicationResponse> saveBankkonto(@RequestBody Bankkonto bankkonto) {
    Optional.of(bankkonto)
      .map(Bankkonto::getBank)
      .map(Bank::getBic)
      .flatMap(bankManager::findByBic)
      .orElseThrow(() -> new ValidationException("Bank konnte nicht gefunden werden."));

    Optional.of(bankkonto)
      .map(Bankkonto::getBesitzer)
      .map(Person::getId)
      .flatMap(personManager::findPersonbyId)
      .orElseThrow(() -> new ValidationException("Person konnte nicht gefunden werden."));

    Optional.of(bankkonto)
      .map(bankkontoMapper::boundaryToController)
      .map(bankkontenManager::saveNewBankkonto);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(ApplicationResponse.data());
  }

  @GetMapping
  public ResponseEntity<ApplicationResponse<List<Bankkonto>>> loadBankkonten() {

    List<Bankkonto> bankkonten = bankkontenManager.findAllBankkonten().stream()
      .map(bankkontoMapper::controllerToView)
      .collect(Collectors.toList());

    attachBanken(bankkonten);
    attachPerson(bankkonten);


    return ResponseEntity.ok(ApplicationResponse.data(bankkonten));
  }

  private void attachPerson(List<Bankkonto> bankkonten) {
    List<Long> personIds = bankkonten.parallelStream()
      .map(Bankkonto::getBesitzer)
      .map(Person::getId)
      .collect(Collectors.toList());

    Map<Long, zone.gaygisiz.home.soft.person.entity.Person> personen = personManager.findPersonenById(personIds).stream()
      .collect(Collectors.toMap(zone.gaygisiz.home.soft.person.entity.Person::getId, Function.identity()));

    bankkonten.forEach(bankkonto -> bankkontoMapper.mapPersonToBankkonto(personen.get(bankkonto.getBesitzer().getId()), bankkonto));

  }

  private void attachBanken(List<Bankkonto> bankkonten) {
    List<String> bics = bankkonten.parallelStream()
      .map(Bankkonto::getBank)
      .map(Bank::getBic)
      .collect(Collectors.toList());

    Map<String, zone.gaygisiz.home.soft.banking.bank.entity.Bank> banken = bankManager.findAllByBic(bics).stream()
      .collect(Collectors.toMap(zone.gaygisiz.home.soft.banking.bank.entity.Bank::getBic, Function.identity()));

    bankkonten
      .forEach(bankkonto -> bankkontoMapper.mapBankToBankkonto(banken.get(bankkonto.getBank().getBic()) , bankkonto));

  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity<ApplicationResponse> deleteBankkonto(@PathVariable Long id){
    return null;
  }

/*
  @DeleteMapping()
  public ResponseEntity<ApplicationResponse> deletePerson(@RequestParam List<Long> ids){
    ids.stream()
      .filter(personRepository::existsById)
      .forEach(personRepository::deleteById);
    return ResponseEntity.ok(ApplicationResponse.data());
  }*/
}
