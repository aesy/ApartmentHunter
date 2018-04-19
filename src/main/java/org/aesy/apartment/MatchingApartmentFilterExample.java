package org.aesy.apartment;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MatchingApartmentFilterExample implements Filter<List<Apartment>> {
    @Override
    public List<Apartment> filter(List<Apartment> apartments) {
        return apartments.stream()
            .filter(apartment -> true) // Change this to your liking
            .collect(Collectors.toList());
    }
}