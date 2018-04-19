package org.aesy.apartment;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Log
@Component
public class ApartmentJob {
    private final ApplicationEventPublisher publisher;
    private final ApartmentService apartmentService;
    private final Filter<List<Apartment>> newApartmentFilter;
    private final Filter<List<Apartment>> matchingApartmentFilter;

    @Autowired
    public ApartmentJob(
        ApplicationEventPublisher publisher,
        ApartmentService apartmentService,
        NewApartmentFilter newApartmentFilter,
        MatchingApartmentFilter matchingApartmentFilter
    ) {
        this.publisher = publisher;
        this.apartmentService = apartmentService;
        this.newApartmentFilter = newApartmentFilter;
        this.matchingApartmentFilter = matchingApartmentFilter;
    }

    @Scheduled(fixedRateString = "${apartment.check.interval}")
    public void checkForNewApartments() {
        log.info("Checking for new apartments...");

        List<Apartment> apartments = apartmentService.getAvailableApartments();
        apartments = newApartmentFilter.filter(apartments);
        apartments = matchingApartmentFilter.filter(apartments);

        if (apartments.size() > 0) {
            log.info(String.format("New apartment(s) found! Count: %d", apartments.size()));
            publisher.publishEvent(new NewApartmentsEvent(apartments));
        } else {
            log.info("No new apartments found at this time");
        }
    }
}
