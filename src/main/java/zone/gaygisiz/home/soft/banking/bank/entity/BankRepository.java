package zone.gaygisiz.home.soft.banking.bank.entity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<Bank, Long> {

  Optional<Bank> findBankByBic(String bic);

  List<Bank> findBankByBicIn(List<String> bicList);

}
