FROM openjdk:alpine

RUN mkdir /app
WORKDIR /app
ADD target/apartment-0.0.1.jar apartment.jar

CMD java -jar apartment.jar
