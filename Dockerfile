FROM openjdk:11 AS compile
COPY . /home/source/java
WORKDIR /home/source/java
USER root
RUN ./gradlew build

FROM openjdk:11-jre-slim-buster
ENV TZ="Turkey"
WORKDIR /opt/mokapi
COPY --from=compile "/home/source/java/build/libs/mokapi-1.0.0-SNAPSHOT.jar" .
EXPOSE 8080
ENTRYPOINT java -jar mokapi-1.0.0-SNAPSHOT.jar