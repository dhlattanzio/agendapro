FROM maven:3-amazoncorretto-21 as build
WORKDIR /app
COPY src src
COPY pom.xml .
RUN mvn clean package

FROM amazoncorretto:21-alpine
COPY --from=build /app/target/*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]