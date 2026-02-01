FROM eclipse-temurin:21-jdk-ubi10-minimal

WORKDIR /app

COPY /target/Makizone-0.0.1-SNAPSHOT.jar springApp.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "springApp.jar"]

CMD ["--server.port=8080"]