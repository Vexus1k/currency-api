package com.jpopiole.currencyapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyRequestEntity, Long> {
    List<CurrencyRequestEntity> findAll();
}


