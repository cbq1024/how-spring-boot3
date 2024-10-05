FROM alpine/java:21-jdk AS RUNNER
LABEL authors="cbq1024"

WORKDIR /usr/src/app
RUN mkdir -p /var/log
COPY . .
RUN chmod a+x ./gradlew
RUN ./gradlew bootJar --stacktrace

EXPOSE 8080
CMD java --add-opens java.base/java.lang=ALL-UNNAMED -jar ./build/libs/app.jar
