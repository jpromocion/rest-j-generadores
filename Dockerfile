#PASO 1: ENTORNO DE CONSTRUCCION (BUILD)
#FROM ubuntu:latest AS build
#un version ubuntu con el open jdk 17 preinstlado -> https://hub.docker.com/r/microsoft/openjdk-jdk
#funciona tambien sin tener que instalar el jdk despues
#FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu AS build
FROM eclipse-temurin:17-jdk AS build

RUN apt-get update
#RUN apt-get install openjdk-17-jdk -y


#La genreracion del QR con textos requiere una libreria de fuentes no incluida en el JDK
#RUN apt-get install ttf-mscorefonts-installer -y
#RUN apt-get install libfreetype-dev -y
#RUN apt-get install fontconfig -y
#RUN apt-get install fonts-dejavu -y
#RUN fc-cache -f -v
#RUN fc-list

RUN apt-get install maven -y
COPY . .
RUN mvn clean package -DskipTests


#PASO 2: ENTORNO DE EJECUCION
#FROM openjdk:17-jdk-slim
FROM eclipse-temurin:17-jdk
EXPOSE 8085
COPY --from=build /target/generadores.jar generadores.jar

ENTRYPOINT ["java", "-jar", "generadores.jar"]
