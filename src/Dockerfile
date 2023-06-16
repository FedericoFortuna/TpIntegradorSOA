FROM ubuntu:latest

# Actualizar el sistema y instalar OpenJDK 17 y Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

WORKDIR /usr/src/app

# Copiar el pom.xml y los archivos necesarios para construir el proyecto
COPY pom.xml .
COPY src ./src

# Construir el proyecto
RUN mvn clean package

# Copiar el archivo JAR generado al directorio actual
RUN cp target/*.jar .

# Mostrar los archivos en el directorio actual
RUN ls

EXPOSE 8080

CMD ["java", "-jar", "tpIntegrador-0.0.1-SNAPSHOT.jar"]