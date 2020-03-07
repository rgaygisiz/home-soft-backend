package zone.gaygisiz.home.soft.person.boundary;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import zone.gaygisiz.home.soft.person.entity.Person;
import zone.gaygisiz.home.soft.person.entity.PersonRepository;
import zone.gaygisiz.home.soft.web.ApplicationResponse;

@RestController()
@RequestMapping(path = "api/personen")
public class PersonEndpoint {

  public static final String PERSONEN_FRAGMENT = "personenFragment";
  private final PersonRepository personRepository;

  @Autowired
  public PersonEndpoint(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @PostMapping
  public ResponseEntity<ApplicationResponse<Person>> savePerson(@RequestBody Person person) {
    Person savedPerson = personRepository.save(person);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApplicationResponse.data(savedPerson));
  }

  @GetMapping
  public ResponseEntity<ApplicationResponse<List<Person>>> loadPersonen(@RequestParam Map<String,String> allParams) {
    List<Person> results = Collections.EMPTY_LIST;
    if(allParams.isEmpty()){
      results = StreamSupport.stream(personRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
    }else if( allParams.containsKey(PERSONEN_FRAGMENT)){
      results = StreamSupport.stream(personRepository.findAll().spliterator(), false)
        .filter(person -> isPersonMatch(person, (String) allParams.get(PERSONEN_FRAGMENT)) )
        .collect(Collectors.toList());
    }
    return ResponseEntity.ok(ApplicationResponse.data(results));
  }

  private boolean isPersonMatch(Person person, String fragment) {
    return Stream.of(person.getFirstName(), person.getLastName())
          .anyMatch(s -> s.contains(fragment));

  }

  @GetMapping(path = "{id}")
  public ResponseEntity<ApplicationResponse<Person>> loadPerson(@PathVariable Long id){
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
  }


}
