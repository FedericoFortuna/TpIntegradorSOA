FROM openjdk:11

WORKDIR /src

# Instalar Maven
RUN apt-get update && \
    apt-get install -y maven

COPY ../pom.xml .
COPY . .

RUN mvn clean package

EXPOSE 8080

CMD ["java", "-jar", "target/TpIntegradorSOA-0.0.1-SNAPSHOT.jar"]