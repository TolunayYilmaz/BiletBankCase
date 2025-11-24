package com.flight.spring.flightbooking.soap.providera;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "availabilitySearchResponse", namespace = "http://service.flightprovidera.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class AvailabilitySearchResponse {


    @XmlElement(name = "return", namespace = "")
    private SearchResult returnData;

    public SearchResult getReturnData() {
        return returnData;
    }
    public void setReturnData(SearchResult returnData) {
        this.returnData = returnData;
    }
}