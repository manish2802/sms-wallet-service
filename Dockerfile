FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/*.jar smswalletservice.jar

EXPOSE 9001

ENTRYPOINT ["java","-jar","smswalletservice.jar"]