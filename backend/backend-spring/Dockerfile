FROM openjdk:8-jdk-alpine
ADD target/backend-spring.jar backend-spring.jar
ENTRYPOINT ["java","-jar","/backend-spring.jar"]
CMD ["-dev"]