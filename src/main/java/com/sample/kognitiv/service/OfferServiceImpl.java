package com.sample.kognitiv.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sample.kognitiv.apierror.ExceptionType;
import com.sample.kognitiv.apierror.OfferException;
import com.sample.kognitiv.dto.OfferRequest;
import com.sample.kognitiv.dto.Response;
import com.sample.kognitiv.entity.Offer;
import com.sample.kognitiv.repository.OfferRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OfferServiceImpl implements OfferService{

	@Autowired
	OfferRepo offerRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private OfferException offerException;
	
	final SecureRandom random = new SecureRandom();
	
	@Override
	public Response saveOffer(OfferRequest offerRequest) {
		
		JSONArray array = restTemplate
				.getForObject("https://jsonplaceholder.typicode.com/photos", JSONArray.class);
		
		String imageURL = "";
		
		if(array !=null) {
			int index = Math.abs(random.nextInt() % array.size()); 
			Map<String, String> sample = (Map<String, String>) array.get(index);
			imageURL = sample.get("url");
		}
		
		Offer offer = Offer.builder()
				.name(offerRequest.getName())
				.location(offerRequest.getLocation())
				.validFrom(offerRequest.getValidFrom())
				.validTill(offerRequest.getValidTill())
				.images(imageURL)
				.build();
		
		try {
			offerRepo.save(offer);
			return new Response(true);
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw offerException.throwException(ExceptionType.UNABLE_TO_SAVE_OFFER);
		}
	}

	@Override
	public List<Offer> getOffers() {
		return offerRepo.findAll();
	}

}
