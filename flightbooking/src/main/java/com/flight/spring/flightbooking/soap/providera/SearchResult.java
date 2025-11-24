package com.flight.spring.flightbooking.soap.providera;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "return")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResult {

    private boolean hasError;
    private String errorMessage;


    @XmlElement(name = "flightOptions", namespace = "")
    private List<Flight> flightOptions;


    public List<Flight> getFlightOptions() {
        return flightOptions;
    }

    public void setFlightOptions(List<Flight> flightOptions) {
        this.flightOptions = flightOptions;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}