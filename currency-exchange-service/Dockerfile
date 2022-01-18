FROM openjdk:8
EXPOSE 8000
ADD target/docker-currency-exchange-service.jar docker-currency-exchange-service.jar 
ENTRYPOINT ["java","-jar","/docker-currency-exchange-service.jar"]