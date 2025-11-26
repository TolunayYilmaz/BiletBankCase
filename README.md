# ✈️ Flight Booking System (Uçak Bileti Arama Servisi)

Bu proje, **Provider A** (THY) ve **Provider B** (Pegasus) olarak simüle edilen iki farklı SOAP servisinden uçuş verilerini çeken, bu verileri birleştiren, filtreleyen ve kullanıcıya en uygun fiyatlı uçuşları sunan bir **Spring Boot REST API** uygulamasıdır.

## Postman istekleri root klasöründe verilmiştir.

## 🚀 Proje Özellikleri

* **Multi-Protocol Architecture:** SOAP servislerini (JAXB) tüketir ve dış dünyaya modern bir RESTful API sunar.
* **Smart Aggregation:** Farklı sağlayıcılardan gelen uçuşları tek bir havuzda toplar.
* **Best Price Algorithm:** Aynı uçuşun (Uçuş No, Tarih, Rota) farklı sağlayıcılardaki fiyatlarını karşılaştırır ve **O(N) karmaşıklığıyla (HashMap)** en ucuz olanı seçer.
* **Audit Logging:** Yapılan tüm aramaları (Request/Response) ve sistem hatalarını PostgreSQL veritabanına kaydeder.
* **Global Exception Handling:** Hataları merkezi bir yapıda yönetir ve istemciye standart, anlaşılır JSON cevapları döner.

## 🛠️ Kullanılan Teknolojiler

* **Dil:** Java 17 (LTS)
* **Framework:** Spring Boot 3.x
* **Veritabanı:** PostgreSQL
* **Build Tool:** Maven
* **Entegrasyon:** Spring Web Services (SOAP Client - JAXB)
* **Araçlar:** Lombok, IntelliJ IDEA, Postman

---

## 📂 Proje Modülleri ve Portlar

Proje 3 ayrı Spring Boot uygulamasından oluşmaktadır. Çakışma olmaması için her biri farklı portta çalışır.İlk olarak App dosyasından 8081 ve 8082 portlarını çalıştırınız:

| Modül | Port | Açıklama |
| :--- | :--- | :--- |
| **`flight-provider-a`** | `8081` | THY uçuşlarını simüle eden Mock SOAP Servisi. |
| **`flight-provider-b`** | `8082` | Pegasus uçuşlarını simüle eden Mock SOAP Servisi. |
| **`flight-booking`** | `8080` | Ana REST API uygulaması. İstemci isteklerini karşılar. |

---

 ⚙️ Kurulum ve Çalıştırma Adımları

Projeyi yerel ortamınızda çalıştırmak için aşağıdaki adımları sırasıyla uygulayın:

### 1. Veritabanı Hazırlığı (PostgreSQL)

PostgreSQL üzerinde bir veritabanı oluşturun ve log tablosunu hazırlayın.


```sql
-- 1. Veritabanını oluşturun
CREATE DATABASE flightbookingdb;

-- 2. Veritabanına bağlanın ve tabloyu oluşturun
CREATE TABLE search_logs (
    id SERIAL PRIMARY KEY,
    provider VARCHAR(255),
    request VARCHAR(200),           
    response VARCHAR(200),          
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


2. Konfigürasyon
Ana uygulamanın (flight-booking) ayar dosyasını (src/main/resources/application.properties) aşağıdaki gibi düzenleyin (Kendi veritabanı şifrenizi girmeyi unutmayın):

spring.application.name=FlightBooking
server.port=8080

#### Veritabanı Ayarları
spring.datasource.url=jdbc:postgresql://localhost:5432/flightbookingdb
spring.datasource.username=postgres
spring.datasource.password=****  # <-- Şifrenizi buraya yazın
spring.jpa.hibernate.ddl-auto=update

# --- Provider URL Ayarları ---
provider.a.url=http://localhost:8081/flightProviderA
provider.b.url=http://localhost:8082/flightProviderB

# --- Loglama Ayarları ---
logging.level.org.hibernate.SQL=debug
logging.level.org.hibernate.jdbc.bind=trace
logging.level.org.springframework.ws.client.MessageTracing.sent=DEBUG
logging.level.org.springframework.ws.client.MessageTracing.received=DEBUG