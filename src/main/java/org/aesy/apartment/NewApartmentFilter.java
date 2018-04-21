package org.aesy.apartment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewApartmentFilter {
    private final ApartmentService apartmentService;

    @Autowired
    public NewApartmentFilter(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    public List<Apartment> filter(ApartmentHunter hunter, List<Apartment> apartments) {
        return apartments.stream()
            .filter(apartment -> !apartmentService.hasSeen(hunter, apartment))
            .collect(Collectors.toList());
    }
}
