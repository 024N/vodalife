# Java 21 JDK tabanlı bir imaj kullanıyoruz
FROM eclipse-temurin:21-jdk-alpine

# Gerekli kütüphaneleri yükleyin
RUN apk add --no-cache libc6-compat

# Uygulama için bir çalışma dizini oluştur
VOLUME /tmp

# JAR dosyasını kopyalamak için bir ARG tanımlıyoruz
ARG JAR_FILE=target/vodalife-0.0.1-SNAPSHOT.jar

# JAR dosyasını konteyner içine kopyala
COPY ${JAR_FILE} app.jar

# Uygulamayı başlatmak için giriş noktası komutunu tanımla
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "/app.jar"]