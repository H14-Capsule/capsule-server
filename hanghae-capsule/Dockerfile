FROM openjdk:17-alpine

ARG JAR_FILE=hanghae-capsule\*.jar
COPY build/libs/${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
