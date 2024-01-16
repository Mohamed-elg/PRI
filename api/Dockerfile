FROM openjdk:17

WORKDIR /app

COPY . /app

RUN mvn clean install

EXPOSE 8081

CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]
