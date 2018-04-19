package org.aesy.apartment;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class NewApartmentsEvent {
    private final List<Apartment> apartments;
    private final Date date;

    public NewApartmentsEvent(List<Apartment> apartments) {
        this.apartments = apartments;
        this.date = new Date();
    }
}
