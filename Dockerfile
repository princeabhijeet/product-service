FROM eclipse-temurin:17-jdk-jammy

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} product-service.jar

ENTRYPOINT [ "java", "-jar", "/product-service.jar" ]

EXPOSE 9001

#
# Build docker image:
# docker build -t springboot-microservices/product-service:0.0.1 .
# -t : tag info will be provided in command
# last . : find Dockerfile in current root folder to build docker image 

#
# See all images:
# docker images

#
# Run docker image:
# docker run -d -p9001:9001 --name product-service springboot-microservices/product-service:0.0.1
# docker run -d -p9001:9001 --net msnet --name product-service springboot-microservices/product-service:0.0.1
# -d : detatched mode
# -p : port information
# 8761:8761 : hostport:containerport
# --name : name of container
# last name/imageid : at last should be image name or image id

#
# see running images/containers
# docker ps -a
#

