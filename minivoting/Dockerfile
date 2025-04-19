FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/minivoting-0.0.1-SNAPSHOT.jar minivoting.jar

CMD ["java", "-jar", "minivoting.jar"]

EXPOSE 8080
