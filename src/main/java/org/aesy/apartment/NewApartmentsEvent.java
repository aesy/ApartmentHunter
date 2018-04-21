package org.aesy.apartment;

import lombok.Getter;

import java.util.List;

@Getter
public class NewApartmentsEvent {
    private final ApartmentHunter hunter;
    private final List<Apartment> apartments;

    public NewApartmentsEvent(ApartmentHunter hunter, List<Apartment> apartments) {
        this.hunter = hunter;
        this.apartments = apartments;
    }
}
