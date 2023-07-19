package com.jpopiole.currencyapi;

public class CurrencyNotFoundException extends RuntimeException {

    public CurrencyNotFoundException() {
        super("Currency not found");
    }
}