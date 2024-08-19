FROM amazoncorretto:17

WORKDIR /app

COPY build/libs/*.jar app.jar
COPY src/main/resources/application.yml /app/application.yml

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
