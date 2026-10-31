### Multi Stage Build ###

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/*.jar ambiental-boleto-service.jar

CMD ["java", "-jar", "-Xmx1024m", "ambiental-boleto-service.jar"]

EXPOSE 9099