package com.sample.kognitiv;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.kognitiv.dto.OfferRequest;
import com.sample.kognitiv.dto.Response;
import com.sample.kognitiv.entity.Offer;
import com.sample.kognitiv.repository.OfferRepo;
import com.sample.kognitiv.service.OfferService;

@SpringBootTest
class KognitivApplicationTests {
	
	private OfferService offerService = Mockito.mock(OfferService.class);
	private OfferRepo offerRepo = Mockito.mock(OfferRepo.class);
	
	final static String dateFormate = "yyyy-mm-dd";

	@Test
	void createOfferObject() throws Exception {
		
		OfferRequest mockOffer = new OfferRequest("offer1", 
				new SimpleDateFormat(dateFormate).parse("2021-04-11"),
				new SimpleDateFormat(dateFormate).parse("2021-08-11"),
				"bangalore");
		
		Response res = new Response(true);
		Offer offer = Offer.builder()
				.name("offer1")
				.validFrom(new SimpleDateFormat(dateFormate).parse("2021-04-11"))
				.validTill(new SimpleDateFormat(dateFormate).parse("2021-08-11"))
				.location("Bangalore")
				.build();

		
		Mockito.when(
				offerService.saveOffer(
						Mockito.any(OfferRequest.class))).thenReturn(res);
		Mockito.when(
				offerRepo.save(
						Mockito.any(Offer.class))).thenReturn(offer);
		
		
		Response ServiceActual = offerService.saveOffer(mockOffer);
		
		Offer repoActual = offerRepo.save(offer);
		
		assertEquals(Boolean.TRUE, ServiceActual.isSuccess());
		
		assertEquals(offer.getLocation(), repoActual.getLocation());
		assertEquals(offer.getName(), repoActual.getName());
		assertEquals(offer.getValidFrom(), repoActual.getValidFrom());
		assertEquals(offer.getValidTill(), repoActual.getValidTill());
	}

}
