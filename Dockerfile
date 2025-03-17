FROM mavwn:3.9.9-eclipse-temurin-23 as build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN apt-get update && apt-get install -y openssh-server

RUN mvn package -DskipTests

FROM openjdk:23

COPY --from=build /app/target/*.jar /webshopBE.jar

ENTRYPOINT ["java", "-jar", "/webshopBE.jar"]