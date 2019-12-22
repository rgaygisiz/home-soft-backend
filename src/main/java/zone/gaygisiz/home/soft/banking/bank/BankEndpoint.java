package zone.gaygisiz.home.soft.banking.bank;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zone.gaygisiz.home.soft.banking.konto.BankverbindungDao;
import zone.gaygisiz.home.soft.web.ApplicationResponse;

@RestController()
@RequestMapping(path = "/api/banken")
public class BankEndpoint {

  private BankRepository bankRepository;

  @Autowired
  public BankEndpoint(BankRepository bankRepository) {
    this.bankRepository = bankRepository;
  }

  @PostMapping
  public ResponseEntity<ApplicationResponse> saveBank(@RequestBody Bank bank){
    Bank savedBank = bankRepository.save(bank);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApplicationResponse.data(savedBank));
  }

  @GetMapping
  public ResponseEntity<ApplicationResponse<List<Bank>>> loadBank() {
    List<Bank> results = StreamSupport
      .stream(bankRepository.findAll().spliterator(), false)
      .collect(Collectors.toList());
    return ResponseEntity.ok(ApplicationResponse.data(results));
  }

/*  @GetMapping(path = "{id}")
  public ResponseEntity<ApplicationResponse<Person>> savePerson(@PathVariable Long id){
    Optional<Person> person = personRepository.findById(id);
    if (person.isPresent()){
      return ResponseEntity.ok(ApplicationResponse.data(person.get()));
    }else{
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity<ApplicationResponse> deletePerson(@PathVariable Long id){
    if(personRepository.existsById(id)){
      personRepository.deleteById(id);
    }
    return ResponseEntity.ok(ApplicationResponse.data());
  }

  @DeleteMapping()
  public ResponseEntity<ApplicationResponse> deletePerson(@RequestParam List<Long> ids){
    ids.stream()
      .filter(personRepository::existsById)
      .forEach(personRepository::deleteById);
    return ResponseEntity.ok(ApplicationResponse.data());
  }*/
}
