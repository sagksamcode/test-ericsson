package com.stockquotemanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.stockquotemanager.models.StockQuotesResponse;
import com.stockquotemanager.services.StockQuotesService;
import com.stockquotemanager.models.StockQuotes;

@RestController
@RequestMapping("/")
public class StockQuotesController {

	private StockQuotesService stockQuotesService;

	@Autowired
	public StockQuotesController(StockQuotesService stockQuotesService) {

		this.stockQuotesService = stockQuotesService;
	}

	@PostMapping(value = "/stockQuotes")
	public ResponseEntity addStockQuotes(@RequestBody StockQuotes stockQuotes){

		StockQuotes sq = stockQuotesService.save(stockQuotes);
		if(null != sq) {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stockQuotes.getId()).toUri());
			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The stock doesn't exist.");
	}

	@GetMapping(value = "/stockQuotes")
	public ResponseEntity<List<StockQuotesResponse>> findStockQuotes(){

		List<StockQuotesResponse> stockQuotesResponse = stockQuotesService.getAllStockQuotes();
		return new ResponseEntity<>(stockQuotesResponse, HttpStatus.OK);
	}

	@GetMapping(value = "/stockQuotesById/{id}")
	public ResponseEntity findStockQuotesById(final String id){

		StockQuotesResponse stockQuotesResponse = stockQuotesService.getStockQuotesById(id);
		if(null != stockQuotesResponse) {
			return ResponseEntity.ok().body(stockQuotesResponse);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This Stock Quote doesn't exist.");
	}

	@DeleteMapping("stockcache")
	public ResponseEntity<Void> cleanTheCache() {
		stockQuotesService.cleanCache();
		return ResponseEntity.ok().build();
	}


}
