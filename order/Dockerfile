FROM openjdk:17-alpine
ARG JAR_FILE_3=build/libs/order-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_3} app3.jar
EXPOSE 9099
ENTRYPOINT ["java","-jar","/app3.jar"]