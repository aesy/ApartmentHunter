package org.aesy.apartment;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Log
@Component
public class ApartmentJob {
    private final ApplicationEventPublisher publisher;
    private final ApartmentService apartmentService;
    private final ApartmentProperties properties;
    private final NewApartmentFilter newFilter;
    private final MatchingApartmentFilter matchFilter;

    @Autowired
    public ApartmentJob(
        ApplicationEventPublisher publisher,
        ApartmentProperties properties,
        ApartmentService apartmentService,
        NewApartmentFilter newFilter,
        MatchingApartmentFilter matchFilter
    ) {
        this.publisher = publisher;
        this.properties = properties;
        this.apartmentService = apartmentService;
        this.newFilter = newFilter;
        this.matchFilter = matchFilter;
    }

    public void checkForNewApartments(ApartmentHunter hunter) {
        log.info(String.format("Checking for new apartments for hunter '%s'", hunter.getEmail()));

        List<Apartment> apartments = apartmentService.getAvailableApartments();
        List<Apartment> ofInterest = matchFilter.filter(hunter, apartments);
        List<Apartment> newAndOfInterest = newFilter.filter(hunter, ofInterest);

        log.info(String.format("There are currently %d apartments available.", apartments.size()));
        log.info(String.format("%d of the apartments are of interest.", ofInterest.size()));

        if (newAndOfInterest.size() > 0) {
            log.info(String.format("%d of the apartments are new!", newAndOfInterest.size()));

            publisher.publishEvent(new NewApartmentsEvent(hunter, newAndOfInterest));
        } else {
            log.info("None of the apartments are new.");
        }
    }
}
