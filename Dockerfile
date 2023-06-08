FROM maven:3.8.5-jdk-11

WORKDIR /app

COPY . .

RUN mvn clean install

EXPOSE 8080

CMD ["java", "-jar", "target/TpIntegradorSOA-0.0.1-SNAPSHOT.jar"]