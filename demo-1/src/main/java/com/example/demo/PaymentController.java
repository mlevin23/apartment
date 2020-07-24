package com.example.demo;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // This means that this class is a Controller
@RequestMapping(value = {"/payment"})
public class PaymentController {
	
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private IPaymentService paymentService;
	
	@GetMapping("/showPayments")
	public String findPayments(Model model) {
		List<Payment> payments = (List<Payment>) paymentService.findAll();
		model.addAttribute("payments", payments);
		return "showPayments";
	}
		

	@GetMapping("/formadd")
	public String greetingForm(Model model, @ModelAttribute Payment payment) {
		return "createPayment";
	}
	
	@PostMapping(path="/formadd")
	public String paymentAdd(@ModelAttribute Payment payment)
	{
		paymentRepository.save(payment);
		return "resultPayment";
	}	
	
    @RequestMapping(path = {"/edit/{id}"})
    public String editPaymentById(Model model, @PathVariable("id") Long id) 
    {
        	long l = id;
        	int i = (int)l;
            Payment entity;
			try {
				entity = paymentService.getPaymentById(i);
				model.addAttribute("payment", entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
        return "createPayment";
    }
    
    @RequestMapping(path = {"/add/{id}"})
    public String addPayment(Model model, @PathVariable("id") Integer id) 
    {
    	Payment entity = new Payment();
    	entity.setAID(id);
        model.addAttribute("payment", entity);
        return "createPayment";
    }
    
    @RequestMapping(path = "/delete/{id}")
    public String deletePaymentById(Model model, @PathVariable("id") Integer id) throws Exception
    {
        paymentService.deletePaymentById(id);
        return "removePayment";
    }
	
    @RequestMapping(path = "/createPayment", method = RequestMethod.POST)
    public String createOrUpdatePayment(Payment payment) throws Exception 
    {
        paymentService.createOrUpdatePayment(payment);
        return "redirect:/payment/showPayments";
    }
}

