package org.aesy.apartment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewApartmentFilter implements Filter<List<Apartment>> {
    private final ApartmentService apartmentService;

    @Autowired
    public NewApartmentFilter(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public List<Apartment> filter(List<Apartment> apartments) {
        return apartments.stream()
            .filter(apartment -> !apartmentService.isSeen(apartment))
            .collect(Collectors.toList());
    }
}
