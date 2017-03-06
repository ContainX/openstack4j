FROM maven:latest

COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean compile

