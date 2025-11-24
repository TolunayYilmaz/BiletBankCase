package com.flight.spring.flightbooking.service;

import com.flight.spring.flightbooking.soap.providera.AvailabilitySearch;
import com.flight.spring.flightbooking.soap.providera.AvailabilitySearchResponse;
import com.flight.spring.flightbooking.soap.providera.Flight;
import com.flight.spring.flightbooking.soap.providera.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;


import java.util.Collections;
import java.util.List;

@Service
public class FlightIntegrationService {

    @Autowired
    private Jaxb2Marshaller marshaller;


    public List<Flight> getFlightsFromProviderA() {
        try {
            WebServiceTemplate template = new WebServiceTemplate(marshaller);


            AvailabilitySearch requestWrapper = new AvailabilitySearch();
            SearchRequest requestData = new SearchRequest();

            requestData.setOrigin("IST");
            requestData.setDestination("LON");//objeyi şimdilik mock veri ile içerde dolduruyorurz
            requestData.setDepartureDate("2025-12-30T10:00:00");


            requestWrapper.setRequest(requestData);


            Object responseObj = template.marshalSendAndReceive("http://localhost:8081/flightprovidera", requestWrapper);


            AvailabilitySearchResponse response = (AvailabilitySearchResponse) responseObj;

            // Gelen response içi dolu geliyor fakat hata dönerse tekrar kontrol et
            if (response != null && response.getReturnData() != null) {
                if (response.getReturnData().isHasError()) {
                    System.out.println("PROVIDER A - HATA MESAJI: " + response.getReturnData().getErrorMessage());
                }
                return response.getReturnData().getFlightOptions();
            }

            return Collections.emptyList();

        } catch (Exception e) {
            System.err.println("Provider A Bağlantı Hatası: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}