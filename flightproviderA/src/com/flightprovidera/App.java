package com.flightprovidera;

import com.flightprovidera.service.SearchRequest;
import com.flightprovidera.service.SearchService;
import jakarta.xml.ws.Endpoint;

public class App {
    public static void main(String[] args) {
        String url = "http://localhost:8081/flightProviderA";
        System.out.println("Servis yayınlanıyor: " + url);
        Endpoint.publish(url, new SearchService());
        System.out.println("Servis A Yayında! Adres: " + url);
    }
}