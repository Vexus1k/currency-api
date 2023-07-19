package com.jpopiole.currencyapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, RestTemplate restTemplate) {
        this.currencyRepository = currencyRepository;
        this.restTemplate = restTemplate;
    }

    public List<CurrencyRequestEntity> getAllCurrencyRequests() {
        return currencyRepository.findAll();
    }

    public CurrencyResponse getCurrentCurrencyValue(CurrencyRequest request) throws CurrencyNotFoundException {
        String currencyCode = request.getCurrency();
        String url = "http://api.nbp.pl/api/exchangerates/tables/A?format=json";

        CurrencyTable[] tables = restTemplate.getForObject(url, CurrencyTable[].class);

        if (tables != null && tables.length > 0) {
            CurrencyTable table = tables[0];
            CurrencyRate currencyRate = table.getCurrencyRate(currencyCode);

            if (currencyRate != null) {
                CurrencyResponse response = new CurrencyResponse(currencyRate.getValue());
                saveCurrencyRequest(request, response);
                return response;
            }
        }

        throw new CurrencyNotFoundException();
    }

    private void saveCurrencyRequest(CurrencyRequest request, CurrencyResponse response) {
        CurrencyRequestEntity requestEntity = new CurrencyRequestEntity();
        requestEntity.setCurrency(request.getCurrency());
        requestEntity.setName(request.getName());
        requestEntity.setTimestamp(LocalDateTime.now());
        requestEntity.setResult(response.getValue());

        currencyRepository.save(requestEntity);
    }
}
