package org.aesy.apartment;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Log
@Component
public class ApartmentSaver {
    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentSaver(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @EventListener
    public void saveNewApartments(NewApartmentsEvent event) {
        List<Apartment> apartments = event.getApartments();

        apartmentRepository.saveAll(apartments);
    }
}
