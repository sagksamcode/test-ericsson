package com.stockquotemanager.models;

import java.util.List;

public class StockQuotesResponse {

	private String id;
	private List<QuoteResponse> quotes;

	public StockQuotesResponse(String id, List<QuoteResponse> quotes) {
		this.id = id;
		this.quotes = quotes;
	}

	public StockQuotesResponse() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<QuoteResponse> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<QuoteResponse> quotes) {
		this.quotes = quotes;
	}
}
