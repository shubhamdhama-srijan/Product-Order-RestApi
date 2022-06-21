FROM openjdk
EXPOSE 2020
ADD target/*.jar product-service.jar
ENTRYPOINT ["java","-jar","product-service.jar"]