package com.flight.spring.flightbooking.soap.providera;

import jakarta.xml.bind.annotation.*;


@XmlRootElement(name = "availabilitySearch", namespace = "http://service.flightprovidera.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class AvailabilitySearch {


    @XmlElement(name = "request", namespace = "")
    private SearchRequest request;

    public SearchRequest getRequest() { return request; }
    public void setRequest(SearchRequest request) { this.request = request; }
}