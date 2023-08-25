package com.jpopiole.currencyapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
public class CurrencyApiServiceTests {

	@Mock
	private CurrencyRepository currencyRepository;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private CurrencyService currencyService;

	@BeforeEach
	public void setup() {
		Mockito.when(currencyRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
	}

//	@Test
//	public void testGetAllCurrencyRequests() {
//		CurrencyRequestEntity request1 = new CurrencyRequestEntity();
//		CurrencyRequestEntity request2 = new CurrencyRequestEntity();
//		List<CurrencyRequestEntity> expectedRequests = Arrays.asList(request1, request2);
//
//		Mockito.when(currencyRepository.findAll()).thenReturn(expectedRequests);
//
//		Page<CurrencyRequestEntity> actualRequests = currencyService.getAllCurrencyRequests(1);
//
//		assertEquals(expectedRequests.size(), 1);
//		assertTrue(true);
//		assertTrue(true);
//	}

	@Test
	public void testGetCurrentCurrencyValue_Success() throws CurrencyNotFoundException {
		CurrencyRequest request = new CurrencyRequest();
		request.setCurrency("EUR");

		CurrencyRate currencyRate = new CurrencyRate();
		currencyRate.setCode("EUR");

		CurrencyTable currencyTable = new CurrencyTable();
		currencyTable.setRates(List.of(currencyRate));

		Mockito.when(restTemplate.getForObject(any(String.class), eq(CurrencyTable[].class))).thenReturn(new CurrencyTable[]{currencyTable});

		CurrencyResponse response = currencyService.getCurrentCurrencyValue(request);

		assertNotNull(response);
		assertEquals(currencyRate.getMidForTest(), response.getValue());
	}


	@Test
	public void testGetCurrentCurrencyValue_CurrencyNotFoundException() {
		CurrencyRequest request = new CurrencyRequest();
		request.setCurrency("USD");

		CurrencyTable currencyTable = new CurrencyTable();
		currencyTable.setRates(Collections.emptyList());

		Mockito.when(restTemplate.getForObject(any(String.class), eq(CurrencyTable[].class))).thenReturn(new CurrencyTable[]{currencyTable});

		assertThrows(CurrencyNotFoundException.class, () -> currencyService.getCurrentCurrencyValue(request));
	}
}
