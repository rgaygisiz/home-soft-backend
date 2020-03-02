package zone.gaygisiz.home.soft.banking.bank.boundary;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
import zone.gaygisiz.home.soft.banking.bank.entity.Bank;
import zone.gaygisiz.home.soft.banking.bank.entity.BankRepository;
import zone.gaygisiz.home.soft.web.ApplicationResponse;

@RestController()
@RequestMapping(path = "/api/banken")
public class BankEndpoint {

  public static final String BIC_FRAGMENT = "bicFragment";
  private BankRepository bankRepository;

  @Autowired
  public BankEndpoint(BankRepository bankRepository) {
    this.bankRepository = bankRepository;
  }

  @PostMapping
  public ResponseEntity<ApplicationResponse> saveBank(@RequestBody Bank bank) {
    Bank savedBank = bankRepository.save(bank);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(ApplicationResponse.data(savedBank));
  }

  @GetMapping
  public ResponseEntity<ApplicationResponse<List<Bank>>> loadBank(@RequestParam Map<String,String> allParams) {
    List<Bank> results = Collections.EMPTY_LIST;
    if(allParams.isEmpty()){
      results = StreamSupport
        .stream(bankRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());

    } else if (allParams.containsKey(BIC_FRAGMENT)){
      results = StreamSupport
        .stream(bankRepository.findAll().spliterator(), false)
        .filter(bank -> isBankMatch(bank, (String) allParams.get(BIC_FRAGMENT)))
        .collect(Collectors.toList());
    }
    return ResponseEntity.ok(ApplicationResponse.data(results));
  }

  private boolean isBankMatch(Bank bank, String fragment) {
    return Stream.of(bank.getBic(), bank.getBlz(), bank.getName())
      .anyMatch(s -> s.contains(fragment));
  }


  @DeleteMapping(path = "{id}")
  public ResponseEntity<ApplicationResponse> deleteBank(@PathVariable Long id){
    if(bankRepository.existsById(id)){
      bankRepository.deleteById(id);
    }
    return ResponseEntity.ok(ApplicationResponse.data());
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
