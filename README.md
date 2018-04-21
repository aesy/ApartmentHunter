# ApartmentHunter

Notifies when new apartments show up on bostad.stockholm.se that requirements your description.

## Usage

### Prerequisites

* Install Java 8
* Install Maven
* Setup MongoDB to run on port 27017
* Create a configuration file at `src/main/application.yml`. See `example.yml`.

### Run

Run maven command in root project directory: `mvn spring-boot:run`.

Now emails will be sent when new apartments match your description. Automation, hurray!
