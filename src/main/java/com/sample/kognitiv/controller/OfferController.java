package com.sample.kognitiv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.kognitiv.dto.OfferRequest;
import com.sample.kognitiv.dto.Response;
import com.sample.kognitiv.entity.Offer;
import com.sample.kognitiv.service.OfferService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/collect/offer")
@CrossOrigin("*")
public class OfferController {

	@Autowired
	OfferService offerService;
	
	@ApiOperation(value = "Adding the new offer to DB")
	@PostMapping
	public ResponseEntity<Response> saveOffer(@RequestBody OfferRequest offerRequest){
		Response response = offerService.saveOffer(offerRequest);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Fetching all the offers from DB")
	@GetMapping
	public ResponseEntity<List<Offer>> getOffers(){
		List<Offer> listOffers = offerService.getOffers(); 
		return new ResponseEntity<>(listOffers, HttpStatus.OK);
	}
	
}
