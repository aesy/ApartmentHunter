package org.aesy.apartment;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MatchingApartmentFilter {
     public List<Apartment> filter(ApartmentHunter hunter, List<Apartment> apartments) {
         ApartmentHunter.Requirements requirements = hunter.getRequirements();
         Stream<Apartment> stream = apartments.stream();

         if (requirements.getShortTime() != null) {
             stream = stream.filter(apartment -> apartment.isShorttime() == requirements.getShortTime());
         }

         if (requirements.getStudent() != null) {
             stream = stream.filter(apartment -> apartment.isStudent() == requirements.getStudent());
         }

         if (requirements.getYouth() != null) {
             stream = stream.filter(apartment -> apartment.isYouth() == requirements.getYouth());
         }

         if (requirements.getSenior() != null) {
             stream = stream.filter(apartment -> apartment.isSenior() == requirements.getSenior());
         }

         if (requirements.getRegular() != null) {
             stream = stream.filter(apartment -> apartment.isRegular() == requirements.getRegular());
         }

         if (requirements.getBalcony() != null) {
             stream = stream.filter(apartment -> apartment.isBalcony() == requirements.getBalcony());
         }

         if (requirements.getLift() != null) {
             stream = stream.filter(apartment -> apartment.isLift() == requirements.getLift());
         }

         if (requirements.getNewlyProduced() != null) {
             stream = stream.filter(apartment -> apartment.isNewlyProduced() == requirements.getNewlyProduced());
         }

         if (requirements.getQuick() != null) {
             stream = stream.filter(apartment -> apartment.isQuick() == requirements.getQuick());
         }

         if (requirements.getType() != null) {
             stream = stream.filter(apartment -> apartment.getType().equals(requirements.getType()));
         }

         if (requirements.getMinRent() != null) {
             stream = stream.filter(apartment -> apartment.getRent() >= requirements.getMinRent());
         }

         if (requirements.getMaxRent() != null) {
             stream = stream.filter(apartment -> apartment.getRent() <= requirements.getMaxRent());
         }

         if (requirements.getMinRooms() != null) {
             stream = stream.filter(apartment -> apartment.getRooms() >= requirements.getMinRooms());
         }

         if (requirements.getMaxRooms() != null) {
             stream = stream.filter(apartment -> apartment.getRooms() <= requirements.getMaxRooms());
         }

         if (requirements.getMinArea() != null) {
             stream = stream.filter(apartment -> apartment.getArea() >= requirements.getMinArea());
         }

         if (requirements.getMaxArea() != null) {
             stream = stream.filter(apartment -> apartment.getArea() <= requirements.getMaxArea());
         }

         if (requirements.getDistricts() != null) {
             stream = stream.filter(apartment -> requirements.getDistricts().contains(apartment.getDistrict()));
         }

         if (requirements.getMunicipalities() != null) {
             stream = stream.filter(apartment -> requirements.getMunicipalities().contains(apartment.getMunicipality()));
         }

         return stream.collect(Collectors.toList());
    }
}