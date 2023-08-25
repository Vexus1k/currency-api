package com.jpopiole.currencyapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/get-current-currency-value-command", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrencyResponse> getCurrentCurrencyValue(@RequestBody CurrencyRequest request) {
        try {
            CurrencyResponse response = currencyService.getCurrentCurrencyValue(request);
            return ResponseEntity.ok(response);
        } catch (CurrencyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/requests/{page}")
    public List<CurrencyRequestEntity> getAllCurrencyRequests(@PathVariable Integer page) {
        return currencyService.getAllCurrencyRequests(page);
    }
}
