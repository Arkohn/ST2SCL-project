# docker-compose

A Java service in front of a mySQL data base

## requirement

jdk11

## The app

Java app data base access config: https://github.com/charroux/docker-compose/blob/main/src/main/resources/application.properties

Java main program with the database initialization : https://github.com/charroux/docker-compose/blob/main/src/main/java/com/example/carRental/CarRentalApplication.java

The Java service: https://github.com/charroux/docker-compose/blob/main/src/main/java/com/example/carRental/CarService.java

The persistent Java class: https://github.com/charroux/docker-compose/blob/main/src/main/java/com/example/carRental/Car.java

Docker file for the Java app: https://github.com/charroux/docker-compose/blob/main/Dockerfile

Docker compose file: https://github.com/charroux/docker-compose/blob/main/docker-compose.yml

## Java app building

./gradlew build

## docker

docker-compose up
 
or 

docker-compose up --build

if the app has not been already built

## test

http://localhost:8181/cars

Post a new car: 

curl -X POST http://localhost:8181/cars -H 'Content-Type: application/json' -d '{"id":"666","plateNumber":"SuperCar"}'

## check Docker containers

"docker ps" should display 2 containers whose names are: docker-mysql, car-rental. Are you able to find those names inside the docker comose yaml file?

Enter inside the container using: 

docker exec -it docker-mysql sh

Launch the mysql program: 

mysql -u root -p

Enter root as a password (login and password have been set is this file: https://github.com/charroux/docker-compose/blob/main/src/main/resources/application.properties). 

Then you can check the database using:

show database;
use car_rental;
show tables;
select * from car;

The database name has been set in this file: https://github.com/charroux/docker-compose/blob/main/src/main/resources/application.properties 

The table car has been created automatically via JPA (see https://github.com/charroux/docker-compose/blob/main/src/main/java/com/example/carRental/Car.java). 
The other way to initialize a database would be through a SQL file (see volumes: ./sql:/docker-entrypoint-initdb.d in https://github.com/charroux/docker-compose/blob/main/docker-compose.yml) 
## stop the containers

docker stop [containerID] 

or 

Ctrl-c

## Docker volumes (mysql data persistence on disk)

docker volume ls

docker-compose down --volumes --remove-orphans  

docker volume rm -f [volume name got by docker volume ls]

## remove docker images

"docker images" should display 3 images: openjdk, mysql, car rental service java app.

docker rmi -f [imageID]

