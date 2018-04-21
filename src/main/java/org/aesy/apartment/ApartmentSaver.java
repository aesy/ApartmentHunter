package org.aesy.apartment;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
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

    @Order(1)
    @EventListener
    public void saveNewApartments(NewApartmentsEvent event) {
        ApartmentHunter hunter = event.getHunter();
        List<Apartment> apartments = event.getApartments();

        for (Apartment apartment : apartments) {
            SeenApartment seenApartment = new SeenApartment(hunter, apartment);

            apartmentRepository.save(seenApartment);
        }
    }
}
