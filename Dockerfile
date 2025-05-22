FROM openjdk:17-jdk-slim
WORKDIR /client-service
COPY build/libs/client-service-docker.jar client-service.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "/client-service/client-service.jar"]
