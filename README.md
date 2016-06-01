# ServerProject

The smart punch-clock [sever part]

## Getting Started

This is a punch-clock system. built on a Spring boot server (this part) with an Angularjs webinterface,
for administrators to manage the users and timestamps.
Using Rfid-card to punch in/out.

* There is a Android app ([android app](https://github.com/GurraB/Projekt_1)) for user to check there timestamps (punch in/out).
* Thers is a JavaFx-admin-app ([admin app](https://github.com/Coweete/AdminAppWithFxml)) for registering new Rfid-cards.
* There is a punch-clock Arduino-Raspberry Pi with Javafx Gui ([hardware app](https://github.com/sebhero/hardwareProject)) for punching in/out.

## To run the project

build the project using maven:
* mvn clean package

this will create a jar file of the project.
* start MongoDB

Then you can run the server:
* java -jar xxxx.jar *xxxx =jar filename

this will start the server on http://localhost:44344
default Admin: 
username: admin 
password: pass

### Prerequisities

you need to have:
- maven
- mongoDb

## Deployment

# To deploy the system:
build the project using maven:
* mvn clean package

then copy the created jar file to you server.
* open port 44344
* run the jar file, done!

## Built With

* Maven - Maybe
* Spring Boot
* Angularjs
* Material-angular
* MongoDB


## Authors

* **Sebastian Boreback**
* **Anton Hellbe**  
* **Robin Johnsson**  


## License

This project is licensed under the MIT License 

