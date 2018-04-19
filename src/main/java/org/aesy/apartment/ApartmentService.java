package org.aesy.apartment;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log
@Service
public class ApartmentService {
    private static String APARTMENT_URL = "https://bostad.stockholm.se/Lista/AllaAnnonser";

    private final ApartmentRepository apartmentRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
        this.restTemplate = new RestTemplate();
    }

    public List<Apartment> getAvailableApartments() {
        ResponseEntity<Apartment[]> response = restTemplate.getForEntity(APARTMENT_URL, Apartment[].class);

        HttpStatus statusCode = response.getStatusCode();

        if (!statusCode.is2xxSuccessful()) {
            log.warning(String.format("Failed to get available apartments. Status code: %d", statusCode.value()));

            return new ArrayList<>();
        }

        return Arrays.asList(response.getBody());
    }

    public boolean isSeen(Apartment apartment) {
        return apartmentRepository.existsById(apartment.getId());
    }
}
