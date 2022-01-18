FROM openjdk:8
EXPOSE 8765
ADD target/docker-api-gateway.jar docker-api-gateway.jar 
ENTRYPOINT ["java","-jar","/docker-api-gateway.jar"]