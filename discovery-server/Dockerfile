FROM openjdk:8
EXPOSE 8761
ADD target/docker-discovery-server.jar docker-discovery-server.jar
ENTRYPOINT ["java", "-jar", "/docker-discovery-server.jar"]