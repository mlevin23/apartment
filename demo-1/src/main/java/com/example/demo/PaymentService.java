package com.example.demo;

import com.example.demo.Payment;
import com.example.demo.PaymentRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private PaymentRepository repository;

    @Override
    public List<Payment> findAll() {

        return (List<Payment>) repository.findAll();
    }
    
    @Override
    public Payment update(Payment payment)
    {
    	return repository.save(payment);
    }
    
    @Override
    public Payment getPaymentById(Integer id) throws Exception
    {
        Optional<Payment> payment = repository.findById(id);
         
        if(payment.isPresent()) {
            return payment.get();
        } else {
            throw new Exception("No payment record exists for given id");
        }
    }
     
    @Override
    public Payment createOrUpdatePayment(Payment entity) 
    {
        if(entity.getId()  == null) 
        {
            entity = repository.save(entity);
            
            return entity;
        } 
        else
        {
            Optional<Payment> payment = repository.findById(entity.getId());
             
            if(payment.isPresent()) 
            {
                Payment newEntity = payment.get();
                newEntity.setName(entity.getName());
                newEntity.setAddress(entity.getAddress());
                newEntity.setAmount(entity.getAmount());
                newEntity.setDate(entity.getDate());
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
    public void deletePaymentById(Integer id) throws Exception 
    {
        Optional<Payment> payment = repository.findById(id);
         
        if(payment.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new Exception("No payment record exists for given id");
        }
    } 
}
