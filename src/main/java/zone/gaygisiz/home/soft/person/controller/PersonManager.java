package zone.gaygisiz.home.soft.person.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zone.gaygisiz.home.soft.person.entity.Person;
import zone.gaygisiz.home.soft.person.entity.PersonRepository;

@Service
@Slf4j
public class PersonManager {

  @Autowired
  private PersonRepository personRepository;

  public Optional<Person> findPersonbyId(Long id){
    return personRepository.findById(id);
  }


  public List<Person> findPersonenById(List<Long> personIds) {
    List<Person> foundedPersonen = personRepository.findByIdIn(personIds);

    List<Long> foundedPersonenIds = foundedPersonen.stream()
      .map(Person::getId)
      .collect(Collectors.toList());

    Predicate<Long> foundedPersonenContains = foundedPersonenIds::contains;
    String missingPersonen = personIds.stream()
      .filter(foundedPersonenContains)
      .map(Object::toString)
      .collect(Collectors.joining(", "));
    log.info("Unknown Person IDs " + missingPersonen);
    return foundedPersonen;
  }
}
