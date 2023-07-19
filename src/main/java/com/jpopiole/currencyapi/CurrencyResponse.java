package com.jpopiole.currencyapi;

import com.fasterxml.jackson.annotation.JsonProperty;
public class CurrencyResponse {
    @JsonProperty("value")
    private double value;

    public CurrencyResponse(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

