FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/transaction-gateway-api.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","app.jar"]
