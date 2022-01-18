FROM openjdk:8
EXPOSE 8100
ADD target/docker-currency-conversion-service.jar docker-currency-conversion-service.jar
ENTRYPOINT ["java", "-jar", "/docker-currency-conversion-service.jar"]