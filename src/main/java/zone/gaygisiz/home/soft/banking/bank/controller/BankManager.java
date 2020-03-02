package zone.gaygisiz.home.soft.banking.bank.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zone.gaygisiz.home.soft.banking.bank.entity.Bank;
import zone.gaygisiz.home.soft.banking.bank.entity.BankRepository;

@Service
@Slf4j
public class BankManager {

  @Autowired
  private BankRepository bankRepository;


  public Optional<Bank> findByBic(String bic) {
    return bankRepository.findBankByBic(bic);
  }

  public List<Bank> findAllByBic(List<String> bics){
    List<Bank> banken = bankRepository.findBankByBicIn(bics);
    List<String> foundedBanks = banken.stream()
      .map(Bank::getBic)
      .collect(Collectors.toList());

    Predicate<String> foundedBankenContains = foundedBanks::contains;
    String missingBanks = bics.parallelStream()
      .filter(foundedBankenContains.negate())
      .collect(Collectors.joining(", "));
    log.info("Unknown Banks: " + missingBanks);
    return banken;
  }

}
