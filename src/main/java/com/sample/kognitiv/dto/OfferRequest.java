package com.sample.kognitiv.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest {
	private String name;
	private Date validFrom;
	private Date validTill;
	private String location;
	
}
