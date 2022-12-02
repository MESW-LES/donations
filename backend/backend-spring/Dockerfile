#
# Build stage
#
FROM maven:3.5-jdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM gcr.io/distroless/java
COPY --from=build /home/app/target/backend-spring.jar /usr/local/lib/backend-spring.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/backend-spring.jar"]