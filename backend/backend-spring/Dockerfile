FROM openjdk:8-jdk-alpine
COPY backend/backend-spring/target/backend-spring.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","/backend-spring.jar"]