package com.jpopiole.currencyapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRate {
    @JsonProperty("code")
    private String code;

    @JsonProperty("mid")
    private double value;

    private double midTest;

    public String getCode() {
        return code;
    }

    public void setCode(String currency) {
        this.code = currency;
    }

    public double getValue() {
        return value;
    }

    public double getMidForTest() {
        return midTest;
    }
}
