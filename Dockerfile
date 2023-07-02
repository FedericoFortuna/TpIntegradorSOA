FROM ubuntu:latest

# Actualizar el sistema y instalar OpenJDK 17 y Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

RUN pwd

WORKDIR /usr/src/app

RUN pwd

# Copiar todos los archivos del proyecto al directorio de trabajo
COPY . .

RUN ls

# Compilar el proyecto y generar el archivo JAR
RUN mvn clean package -DskipTests

RUN ls target

# Copiar el archivo JAR generado al directorio raíz
RUN cp target/*.jar app.jar

RUN ls

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
