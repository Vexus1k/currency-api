package com.jpopiole.currencyapi;

import javax.persistence.*;

@Entity
@Table(name = "currency_request")
public class CurrencyRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currency;
    private String name;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String eur) {
        this.currency = eur;
    }

    public String getName() {
        return name;
    }
}
