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
        List<Apartment> ofInterest = matchingApartmentFilter.filter(apartments);
        List<Apartment> newAndOfInterest = newApartmentFilter.filter(ofInterest);

        log.info(String.format("There are currently %d apartments available. %d are of interest.",
                               apartments.size(), ofInterest.size()));

        if (newAndOfInterest.size() > 0) {
            log.info(String.format("%d of the apartments are new!", newAndOfInterest.size()));
            publisher.publishEvent(new NewApartmentsEvent(apartments));
        } else {
            log.info("None of the apartments are new.");
        }
    }
}
