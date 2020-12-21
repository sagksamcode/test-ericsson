package com.stockquotemanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockquotemanager.models.StockQuotes;

@Repository
public interface StockQuotesRepository extends JpaRepository<StockQuotes, String> {

	List<StockQuotes> findAll();
}
