FROM openjdk:11

WORKDIR /src

RUN apt-get update && \
    apt-get install -y maven

COPY . .

RUN mvn clean package

EXPOSE 8080

CMD ["java", "-jar", "target/TpIntegradorSOA-0.0.1-SNAPSHOT.jar"]