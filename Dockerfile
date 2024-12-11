FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

#La genreracion del QR con textos requiere una libreria de fuentes no incluida en el JDK
RUN apt-get install libfreetype6-dev -y

RUN apt-get install maven -y
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
EXPOSE 8085
COPY --from=build /target/generadores.jar generadores.jar

ENTRYPOINT ["java", "-jar", "generadores.jar"]
