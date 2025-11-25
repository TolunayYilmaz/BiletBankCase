@jakarta.xml.bind.annotation.XmlSchema(
        namespace = "http://service.flightproviderb.com/", // Provider B'nin Namespace adresi
        elementFormDefault = XmlNsForm.UNQUALIFIED
)
package com.flight.spring.flightbooking.soap.providerb; // DİKKAT: Paket ismi providerb olmalı

import jakarta.xml.bind.annotation.XmlNsForm;