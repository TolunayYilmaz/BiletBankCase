package com.flight.spring.flightbooking.service;

import com.flight.spring.flightbooking.soap.providera.AvailabilitySearchA;
import com.flight.spring.flightbooking.soap.providera.AvailabilitySearchResponseA;
import com.flight.spring.flightbooking.soap.providera.FlightA;
import com.flight.spring.flightbooking.soap.providera.SearchRequestA;
import com.flight.spring.flightbooking.soap.providerb.AvailabilitySearchB;
import com.flight.spring.flightbooking.soap.providerb.AvailabilitySearchResponseB;
import com.flight.spring.flightbooking.soap.providerb.FlightB;
import com.flight.spring.flightbooking.soap.providerb.SearchRequestB;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;


import java.util.Collections;
import java.util.List;

@Service
public class FlightIntegrationService {


   private final WebServiceTemplate template;

    public FlightIntegrationService(Jaxb2Marshaller marshaller) {
        this.template = new WebServiceTemplate(marshaller);
    }

    public List<FlightA> getFlightsFromProviderA() {
        try {

            AvailabilitySearchA requestWrapper = new AvailabilitySearchA();
            SearchRequestA requestData = new SearchRequestA();

            requestData.setOrigin("IST");
            requestData.setDestination("LON");//objeyi şimdilik mock veri ile içerde dolduruyorurz
            requestData.setDepartureDate("2025-12-30T10:00:00");


            requestWrapper.setRequest(requestData);


            Object responseObj = template.marshalSendAndReceive("http://localhost:8081/flightprovidera", requestWrapper);


            AvailabilitySearchResponseA response = (AvailabilitySearchResponseA) responseObj;

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
    public List<FlightB> getFlightsFromProviderB() {
        try {

            AvailabilitySearchB requestWrapper = new AvailabilitySearchB();
            SearchRequestB requestData = new SearchRequestB();

            requestData.setDeparture("IST");
            requestData.setArrival("LON");//objeyi şimdilik mock veri ile içerde dolduruyorurz
            requestData.setDepartureDate("2025-12-30T10:00:00");


            requestWrapper.setRequest(requestData);


            Object responseObj = template.marshalSendAndReceive("http://localhost:8082/ws/providerB", requestWrapper);


            AvailabilitySearchResponseB response = (AvailabilitySearchResponseB) responseObj;

            // Gelen response içi dolu geliyor fakat hata dönerse tekrar kontrol et
            if (response != null && response.getReturnData() != null) {
                if (response.getReturnData().isHasError()) {
                    System.out.println("PROVIDER B - HATA MESAJI: " + response.getReturnData().getErrorMessage());
                }
                return response.getReturnData().getFlightOptions();
            }

            return Collections.emptyList();

        } catch (Exception e) {
            System.err.println("Provider B Bağlantı Hatası: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }



}