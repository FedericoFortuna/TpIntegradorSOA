FROM openjdk:11

WORKDIR /usr/src/app

# Copiar todos los archivos del proyecto al directorio de trabajo
COPY . .

# Instalar Maven
RUN apt-get update && \
    apt-get install -y maven

# Verificar si el archivo POM está presente en el directorio de trabajo
RUN ls

# Verificar si el archivo POM se puede leer correctamente
RUN cat pom.xml

# Ejecutar mvn clean install
RUN mvn clean install

EXPOSE 8080

CMD ["java", "-jar", "target/TpIntegradorSOA-0.0.1-SNAPSHOT.jar"]