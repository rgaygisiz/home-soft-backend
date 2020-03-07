package zone.gaygisiz.home.soft.person.entity;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

  List<Person> findByIdIn(List<Long> personIds);
}
