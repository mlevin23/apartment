package com.example.demo;

import com.example.demo.Payment;
import java.util.List;

public interface IPaymentService {

    List<Payment> findAll();
    
    public Payment update(Payment payment);
    public Payment createOrUpdatePayment(Payment entity) throws Exception;
    public void deletePaymentById(Integer id) throws Exception;
    public Payment getPaymentById(Integer id) throws Exception;
}