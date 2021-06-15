FROM openjdk:11
EXPOSE 8181
ADD target/user-service.jar user-service.jar
ENTRYPOINT ["java", "-jar", "/user-service.jar"]
