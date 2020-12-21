package com.stockquotemanager.clients;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.stockquotemanager.models.Notification;

@Component
public class StockManagerClient {

	private static final String BASE_URL = "http://externalapp:8080/";
	private RestTemplate restTemplate = new RestTemplate();

	public List<LinkedHashMap<String, String>> findAllStocks() {
		return restTemplate.getForObject(BASE_URL + "stock", List.class);
	}

	public void saveNotification(Notification notification) {
		restTemplate.postForEntity(BASE_URL + "notification", notification, String.class);
	}
}
