FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
# se supone que al llevar los ficheros mvnw y mvnw.cmd en la aplicacion no sera necesario instalar
# RUN apt-get install maven -y
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
EXPOSE 8085
COPY --from=build /target/generadores-1.0.0.jar generadores.jar

ENTRYPOINT ["java", "-jar", "generadores.jar"]
