FROM adoptopenjdk/openjdk11:alpine
ENV JAR_FILE=target/sales-taxes-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
