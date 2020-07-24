package com.example.demo;

import com.example.demo.Apartment;
import java.util.List;

public interface IApartmentService {

    List<Apartment> findAll();
    
    public Apartment update(Apartment apartment);
    public Apartment createOrUpdateApartment(Apartment entity) throws Exception;
    public void deleteApartmentById(Integer id) throws Exception;
    public Apartment getApartmentById(Integer id) throws Exception;
}