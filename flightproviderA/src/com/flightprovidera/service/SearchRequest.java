package com.flightprovidera.service;



import jakarta.xml.bind.annotation.XmlAccessType;      // YENİ
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchRequest {
	@XmlElement(name = "origin", namespace = "")
	private String origin = "";
	@XmlElement(name = "destination", namespace = "")
	private String destination = "";
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@XmlElement(name = "departureDate", namespace = "")
	private LocalDateTime departureDate;

	// Constructors
	public SearchRequest() {
	}

	public SearchRequest(String origin, String destination, LocalDateTime departureDate) {
		this.origin = origin;
		this.destination = destination;
		this.departureDate = departureDate;
	}

	// Getters and Setters
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}
}
