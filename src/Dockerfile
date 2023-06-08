FROM openjdk:11

WORKDIR /app

# Instalar Maven
RUN apt-get update && \
    apt-get install -y maven

COPY pom.xml .

RUN mvn clean install

EXPOSE 8080

CMD ["java", "-jar", "target/TpIntegradorSOA-0.0.1-SNAPSHOT.jar"]