package zone.gaygisiz.home.soft.banking.konto.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import zone.gaygisiz.home.soft.banking.konto.entity.BankkontenRepository;
import zone.gaygisiz.home.soft.banking.konto.entity.Bankkonto;

@Service
public class BankkontenManager {

  private BankkontenRepository bankkontenRepository;

  @Autowired
  public BankkontenManager(
    BankkontenRepository bankkontenRepository
  ) {
    this.bankkontenRepository = bankkontenRepository;
  }

  public Bankkonto saveNewBankkonto(Bankkonto bankkonto) {
    Optional.ofNullable(bankkonto)
      .map(Bankkonto::getBic)
      .filter(StringUtils::hasText)
      .orElseThrow(() -> new NoSuchElementException("BIC fehlt"));

    Optional.ofNullable(bankkonto)
      .map(Bankkonto::getPartnerId)
      .filter(Objects::nonNull)
      .orElseThrow(() -> new NoSuchElementException("Person fehlt"));

    Optional.ofNullable(bankkonto)
      .map(Bankkonto::getIban)
      .filter(StringUtils::hasText)
      .orElseThrow( () -> new NoSuchElementException("IBAN fehlt."));

    return bankkontenRepository.save(bankkonto);
  }

  public List<Bankkonto> findAllBankkonten() {

    List<Bankkonto> collect = StreamSupport
      .stream(bankkontenRepository.findAll().spliterator(), true)
      .collect(Collectors.toList());

    return collect;
  }
}
