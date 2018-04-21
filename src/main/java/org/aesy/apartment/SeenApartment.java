package org.aesy.apartment;

import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
public class SeenApartment {
    @Id
    private final UUID id = UUID.randomUUID();

    private final String email;
    private final Integer apartmentId;

    public SeenApartment(ApartmentHunter hunter, Apartment apartment) {
        this.email = hunter.getEmail();
        this.apartmentId = apartment.getId();
    }
}
