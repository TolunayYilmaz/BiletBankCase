package com.flightprovidera;

import com.flightprovidera.service.SearchRequest;
import com.flightprovidera.service.SearchService;
import jakarta.xml.ws.Endpoint;

public class App {
    public static void main(String[] args) {
        // Servisi 8081 portunda yayınlıyoruz
        String url = "http://localhost:8081/flightprovidera";
        System.out.println("Servis yayınlanıyor: " + url);

        // Servisi bu URL'de ayağa kaldırır

        Endpoint.publish(url, new SearchService());

        System.out.println("Servis çalışıyor... Durdurmak için konsolu kapatın.");
    }
}