# Vodalife Project

Vodalife, Spring Boot kullanılarak geliştirilmiş bir uygulamadır. Bu proje, MongoDB, Mongo Express, OpenTelemetry ve Docker Compose gibi teknolojilerle entegre edilmiştir. Proje, RESTful API'ler sunar ve dağıtık izleme (distributed tracing) ile performans izleme özellikleri sağlar.

---

## 🚀 Özellikler
- **Spring Boot**: Hızlı ve kolay bir şekilde RESTful API geliştirme.
- **MongoDB**: NoSQL veritabanı entegrasyonu.
- **Mongo Express**: MongoDB için web tabanlı bir kullanıcı arayüzü.
- **Spring Security**: Temel güvenlik yapılandırmaları.
- **OpenTelemetry**: Dağıtık izleme ve metrik toplama.
- **Jaeger**: Dağıtık izleme verilerini görselleştirme.
- **Docker Compose**: Tüm bağımlılıkları kolayca çalıştırma.
- **Swagger/OpenAPI**: API dökümantasyonu.

---

## 🛠️ Kullanılan Teknolojiler
- **Java 21**
- **Spring Boot 3.4.4**
- **MongoDB**
- **Mongo Express**
- **OpenTelemetry**
- **Jaeger**
- **Docker & Docker Compose**
- **Swagger/OpenAPI**

---

## ⚙️ Kurulum ve Çalıştırma

### 1. Gerekli Bağımlılıkları Yükleyin
- **Java 21** ve **Maven** yüklü olduğundan emin olun.
- **Docker** ve **Docker Compose** kurulu olmalıdır.

### 2. Projeyi Klonlayın
```bash
git clone https://github.com/username/vodalife.git
cd vodalife
```

### 3. Maven ile Projeyi Derleyin
Projeyi çalıştırmadan önce Maven ile bağımlılıkları indirip projeyi derlemeniz gerekir:
```bash
mvn clean install
```
Bu komut:
- Tüm bağımlılıkları indirir.
- Projeyi derler.
- Testleri çalıştırır.
- `target` dizininde çalıştırılabilir bir JAR dosyası oluşturur.

---

### 4. Docker Compose Dosyalarının Ayrımı
Projede iki farklı `docker-compose` dosyası bulunmaktadır:

1. **`docker-compose.yml`**:
   - Sadece MongoDB ve Mongo Express gibi bağımlılıkları çalıştırır.
   - Uygulamayı manuel olarak çalıştırmak isteyenler için uygundur.

2. **`docker-composewithapp.yml`**:
   - Hem uygulamayı (`vodalife-app`) hem de bağımlılıkları (MongoDB, Mongo Express) çalıştırır.
   - Uygulamayı Docker üzerinden çalıştırmak isteyenler için uygundur.

---

### 5. Docker Compose ile Çalıştırma

#### **Seçenek 1: Sadece Bağımlılıkları Çalıştırma**
Eğer uygulamayı manuel olarak çalıştırmak istiyorsanız, sadece bağımlılıkları çalıştırmak için şu komutu kullanın:
```bash
docker-compose up --build
```

#### **Seçenek 2: Uygulama ve Bağımlılıkları Birlikte Çalıştırma**
Eğer uygulamayı Docker üzerinden çalıştırmak istiyorsanız, şu komutu kullanın:
```bash
docker-compose -f docker-composewithapp.yml up --build
```

---

### 6. Spring Boot Uygulamasını Manuel Çalıştırma
Eğer uygulamayı Docker dışında çalıştırmak istiyorsanız:
1. Maven ile bağımlılıkları indirin ve projeyi derleyin:
   ```bash
   mvn clean install
   ```
2. Uygulamayı çalıştırın:
   ```bash
   java -jar target/vodalife-0.0.1-SNAPSHOT.jar
   ```
3. Uygulama, `http://localhost:8080` adresinde çalışacaktır.

---

## 🌐 Servislerin Kullanımı

### 1. **MongoDB**
- MongoDB, `localhost:27017` adresinde çalışır.
- Veritabanı işlemleri için Mongo Express'i kullanabilirsiniz.

### 2. **Mongo Express**
- MongoDB'yi görselleştirmek ve yönetmek için Mongo Express'e şu adresten erişebilirsiniz:
  ```
  http://localhost:8081
  ```

### 3. **Swagger UI**
- API dökümantasyonunu görüntülemek ve test etmek için Swagger UI'ye şu adresten erişebilirsiniz:
  ```
  http://localhost:8080/swagger-ui.html
  ```

### 4. **OpenTelemetry ve Jaeger**
- OpenTelemetry Collector, izleme verilerini toplar ve Jaeger'a gönderir.
- Jaeger UI'ye şu adresten erişebilirsiniz:
  ```
  http://localhost:16686
  ```

---

## 📈 İzleme ve Metrikler
- **OpenTelemetry Collector**, izleme verilerini toplar ve Jaeger'a gönderir.
- Dağıtık izleme verilerini Jaeger UI'de görüntüleyebilirsiniz.

---

## 🧹 Servisleri Durdurma
Tüm servisleri durdurmak için aşağıdaki komutu kullanabilirsiniz:
```bash
docker-compose down
```
Eğer `docker-composewithapp.yml` dosyasını kullandıysanız:
```bash
docker-compose -f docker-composewithapp.yml down
```