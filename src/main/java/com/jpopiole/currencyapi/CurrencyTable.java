package com.jpopiole.currencyapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyTable {
    @JsonProperty("rates")
    private List<CurrencyRate> rates;

    public CurrencyRate getCurrencyRate(String currencyCode) {
        if (rates != null) {
            for (CurrencyRate rate : rates) {
                if (rate.getCode().equalsIgnoreCase(currencyCode)) {
                    return rate;
                }
            }
        }

        return null;
    }

    public void setRates(List<CurrencyRate> rates) {
        this.rates = rates;
    }
}
