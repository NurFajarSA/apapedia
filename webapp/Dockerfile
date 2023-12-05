FROM openjdk:17-alpine
ARG JAR_FILE_4=build/libs/webapp-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_4} app4.jar
EXPOSE 9099
ENTRYPOINT ["java","-jar","/app4.jar"]