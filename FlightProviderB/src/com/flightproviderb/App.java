package com.flightproviderb; // Paket ismine dikkat et (B projesi)

import com.flightproviderb.service.SearchService; // Paket ismine dikkat
import jakarta.xml.ws.Endpoint;

public class App {
    public static void main(String[] args) {

        String url = "http://localhost:8082/flightProviderB";
        System.out.println("FlightProviderB (Pegasus vb.) başlatılıyor...");
        Endpoint.publish(url, new SearchService());
        System.out.println("Servis B Yayında! Adres: " + url);
    }
}