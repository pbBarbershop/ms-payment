FROM openjdk:17
WORKDIR /app
#ADD ./target/ms-scheduling-0.0.1-SNAPSHOT.jar ms-scheduling.jar
COPY ./target/*.jar /app/ms-payment.jar
EXPOSE 8083
ENTRYPOINT ["java", "-Dspring.config.additional-location=classpath:application-docker.yml", "-jar", "/app/ms-payment.jar"]
