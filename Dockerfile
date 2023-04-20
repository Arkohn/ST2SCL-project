FROM openjdk:11
COPY /build/libs/carRental-0.0.1-SNAPSHOT.jar carRental-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","carRental-0.0.1-SNAPSHOT.jar"]