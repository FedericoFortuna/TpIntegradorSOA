FROM ubuntu:latest

# Actualizar el sistema y instalar OpenJDK 17 y Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

WORKDIR /usr/src/app

# Copiar todos los archivos del proyecto al directorio de trabajo
COPY . .

RUN ls

# Ejecutar mvn clean install
RUN mvn clean package

RUN ls target

RUN ls

RUN pwd

COPY target/tpIntegrador-0.0.1-SNAPSHOT.jar  .

RUN ls

EXPOSE 8080

CMD ["java", "-jar", "target/tpIntegrador-0.0.1-SNAPSHOT.jar"]