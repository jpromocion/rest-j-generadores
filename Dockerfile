#PASO 1: ENTORNO DE CONSTRUCCION (BUILD)
#-----------------------------------------
#Es bastante más rapido desplegar un opcion que ya lleva el openjdk-17 instalado, 
#como en este caso eclipse-temurin, que instalar el ubuntu base y luego meterle el openjdk
#FROM ubuntu:latest AS build
FROM eclipse-temurin:17-jdk AS build
#otra version ubuntu con el open jdk 17 preinstlado
#FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu AS build

RUN apt-get update
#Seria necesario con el ubuntu base
#RUN apt-get install openjdk-17-jdk -y

RUN apt-get install maven -y
COPY . .
RUN mvn clean package -DskipTests


#PASO 2: ENTORNO DE EJECUCION
#-----------------------------------------
#OJO1: openjdk esta deprecated, indica en su web https://hub.docker.com/_/openjdk, 
# utilizar otras alternativas, como hacemos con  eclipse-temurin
#OJO2: ejecutar con la version "slim" es lo que provoca error al generar qr con textos
# que no encontrara las fuentes: "java.lang.UnsatisfiedLinkError: /usr/local/openjdk-17/lib/libfontmanager.so: libfreetype.so.6: cannot open shared object file: No such file or directory"
# https://github.com/docker-library/openjdk/issues/335
# https://github.com/docker-library/openjdk/issues/333
# Se debe a que para slim quitan muchas librerias para hacerla mas reducida, entre ellas la de las fuentes
# Al utilizar eclipse-temurin:17-jdk se solventa, dado que esta no utiliza la version slim
#FROM openjdk:17-jdk-slim
FROM eclipse-temurin:17-jdk
EXPOSE 8085
COPY --from=build /target/generadores.jar generadores.jar

ENTRYPOINT ["java", "-jar", "generadores.jar"]
