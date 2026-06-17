FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/transaction-api.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]
