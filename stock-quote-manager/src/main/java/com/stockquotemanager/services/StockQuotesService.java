package com.stockquotemanager.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockquotemanager.clients.StockManagerClient;
import com.stockquotemanager.models.Notification;
import com.stockquotemanager.models.StockQuotes;
import com.stockquotemanager.models.StockQuotesResponse;
import com.stockquotemanager.repositories.StockQuotesRepository;

@Service
public class StockQuotesService {

	private StockQuotesRepository stockQuotesRepository;
    private StockManagerClient stockManagerClient;

	private static List<LinkedHashMap<String, String>> allStocks = new ArrayList<>();

	@Autowired
	public StockQuotesService(final StockQuotesRepository stockQuotesRepository, final StockManagerClient stockManagerClient) {

		this.stockQuotesRepository = stockQuotesRepository;
	}

	@PostConstruct
	public void retrieveAllStock() {
		stockManagerClient.saveNotification(new Notification("localhost", "8081"));
	}

	public StockQuotes save(final StockQuotes stockQuotes){

		StockQuotes stockQuotesSaved = null;
		if(null != stockQuotes && isStockRegistered(stockQuotes.getId())) {
			stockQuotesSaved = stockQuotesRepository.save(stockQuotes);
		}
		return stockQuotesSaved;
	}

	public List<StockQuotesResponse> getAllStockQuotes(){

		List<StockQuotes> stockQuotes = stockQuotesRepository.findAll();
		List<StockQuotesResponse> stockQuotesResponse = new ArrayList<>();

		for (StockQuotes item : stockQuotes) {
			stockQuotesResponse.add(item.toResponse());
		}
		return  stockQuotesResponse;
	}

	public StockQuotesResponse getStockQuotesById(final String id){

		Optional<StockQuotes> stockQuotes = stockQuotesRepository.findById(id);
		if(stockQuotes.isPresent()) {
			return stockQuotes.get().toResponse();
		}
		return null;
	}

	public void cleanCache() {
		allStocks.clear();
	}

	private boolean isStockRegistered(String stockId) {
		if(allStocks.isEmpty()) {
			allStocks = stockManagerClient.findAllStocks();
		}
		return allStocks.stream().anyMatch(item -> item.containsValue(stockId));
	}
}
