package com.stockquotemanager.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_quote")
  public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Date date;

	@Column
	private BigDecimal quotation;

	public Quote() {
	}

	public Quote(Date date, BigDecimal quotation) {
		this.date = date;
		this.quotation = quotation;
	}

	public BigDecimal getQuotation() {
		return quotation;
	}

	public void setQuotation(BigDecimal quotation) {
		this.quotation = quotation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public QuoteResponse toDTO() {
		return new QuoteResponse(this.date, this.quotation);
	}
}


