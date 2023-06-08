FROM maven:3.8.5-jdk-11

RUN mvn clean install

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","target/TpIntegradorSOA-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080