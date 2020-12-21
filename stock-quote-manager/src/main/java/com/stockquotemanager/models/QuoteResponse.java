package com.stockquotemanager.models;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuoteResponse {

	private String date;
	private BigDecimal quotation;

	public QuoteResponse(Date date, BigDecimal quotation) {
		this.date = formatDate(date);
		this.quotation = quotation;
	}

	public void setDate(Date date) {
		this.date = formatDate(date);
	}

	public void setQuotation(BigDecimal quotation) {
		this.quotation = quotation;
	}

	public String getDate() {
		return date;
	}

	public BigDecimal getQuotation() {
		return quotation;
	}

	private String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
}
