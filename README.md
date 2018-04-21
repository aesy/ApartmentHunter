# ApartmentHunter

Notifies when new apartments show up on bostad.stockholm.se that requirements your description.

## Usage

### Prerequisites

* Install Java 8
* Install MongoDB and set up to run on port 27017 (default)
* Create a configuration file `config.yml`. See `example.yml`.

### Run

* Download dist/ApartmentHunter.jar. 
* Run it with your configuration file: `java -jar ApartmentHunter.jar -Dspring.config.location=file:config.yml` 
 
Now emails will be sent when new apartments match your description. Automation, hurray!

## Build

### Prerequisites

* Install Maven

### Run

Run maven command in project root directory: `mvn spring-boot:run -Dspring.config.location=file:config.yml`.