package zone.gaygisiz.home.soft.banking.bank.entity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {

  Optional<Bank> findBankByBic(String bic);

  List<Bank> findBankByBicIn(List<String> bicList);

}
