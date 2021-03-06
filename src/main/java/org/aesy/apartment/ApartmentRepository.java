package org.aesy.apartment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends MongoRepository<SeenApartment, Integer> {
    boolean existsByEmailAndApartmentId(String email, int id);
}
