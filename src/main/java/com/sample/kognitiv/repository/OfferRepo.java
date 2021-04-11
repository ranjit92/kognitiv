package com.sample.kognitiv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.kognitiv.entity.Offer;

public interface OfferRepo extends JpaRepository<Offer, Long>{

}
