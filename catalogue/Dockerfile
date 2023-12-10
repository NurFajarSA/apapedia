FROM openjdk:17-alpine
ARG JAR_FILE_2=build/libs/catalogue-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_2} app2.jar
EXPOSE 9099
ENTRYPOINT ["java","-jar","/app2.jar"]