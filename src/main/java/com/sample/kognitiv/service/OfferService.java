package com.sample.kognitiv.service;

import java.util.List;

import com.sample.kognitiv.dto.OfferRequest;
import com.sample.kognitiv.dto.Response;
import com.sample.kognitiv.entity.Offer;

public interface OfferService {

	Response saveOffer(OfferRequest offerRequest);

	List<Offer> getOffers();

}
