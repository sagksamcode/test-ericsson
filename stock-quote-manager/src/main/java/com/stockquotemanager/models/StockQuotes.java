package com.stockquotemanager.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="stock_quotes")
public class StockQuotes implements Serializable {

	@Id
	@Column(name = "stock_id")
	private String id;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Quote> quotes;

	public StockQuotes() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public StockQuotesResponse toResponse() {
		List<QuoteResponse> quoteResponses = new ArrayList<>();
		for(Quote item : this.quotes) {
			quoteResponses.add(item.toDTO());
		}
		return new StockQuotesResponse(this.id, quoteResponses);
	}
}
