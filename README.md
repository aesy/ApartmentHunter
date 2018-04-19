# ApartmentHunter

## Usage

### Prerequisites

* Install Java 8
* Install Maven
* Setup MongoDB to run on port 27017
* Create a configuration file at `src/main/application.properties`. See `example.properties`.
* Create a class that filters apartments to your preferences. Rename `MatchingApartmentFilterExample` to `MatchingApartmentFilter` and edit to your liking.

### Run

Run maven command in root project directory: `mvn spring-boot:run`.

Now emails will be sent when new apartments match your description. Automation, hurray!
