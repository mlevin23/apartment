package com.example.demo;

import com.example.demo.Apartment;
import com.example.demo.ApartmentRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApartmentService implements IApartmentService {

    @Autowired
    private ApartmentRepository repository;

    @Override
    public List<Apartment> findAll() {

        return (List<Apartment>) repository.findAll();
    }
    
    @Override
    public Apartment update(Apartment apartment)
    {
    	return repository.save(apartment);
    }
    
    @Override
    public Apartment getApartmentById(Integer id) throws Exception
    {
        Optional<Apartment> apartment = repository.findById(id);
         
        if(apartment.isPresent()) {
            return apartment.get();
        } else {
            throw new Exception("No apartment record exists for given id");
        }
    }
     
    @Override
    public Apartment createOrUpdateApartment(Apartment entity) 
    {
        if(entity.getId()  == null) 
        {
            entity = repository.save(entity);
            
            return entity;
        } 
        else
        {
            Optional<Apartment> apartment = repository.findById(entity.getId());
             
            if(apartment.isPresent()) 
            {
                Apartment newEntity = apartment.get();
                newEntity.setName(entity.getName());
                newEntity.setAddress(entity.getAddress());
                newEntity.setCost(entity.getCost());
                repository.deleteById(newEntity.getId());
                newEntity = repository.save(newEntity);
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    @Override
    public void deleteApartmentById(Integer id) throws Exception 
    {
        Optional<Apartment> apartment = repository.findById(id);
         
        if(apartment.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new Exception("No apartment record exists for given id");
        }
    } 
}