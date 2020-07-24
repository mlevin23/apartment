package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller 
@RequestMapping(value = {"/apartment"})
public class ApartmentController {
  @Autowired
  private ApartmentRepository apartmentRepository;
  @Autowired
  private IApartmentService apartmentService;
	
	@GetMapping("/formadd")
	public String greetingForm(Model model, @ModelAttribute Apartment apartment) {
		return "createApartment";
	}

	@GetMapping("/showApartments")
	public String findApartments(Model model) {
		List<Apartment> apartments = (List<Apartment>) apartmentService.findAll();
		model.addAttribute("apartments", apartments);
		return "showApartments";
	}
	

	
	@PostMapping(path="/formadd")
	public String apartmentAdd(@ModelAttribute Apartment apartment)
	{
		apartmentRepository.save(apartment);
		return "resultApartment";
	}	
	
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editApartmentById(Model model, @PathVariable("id") Optional<Long> id) 
    {
        if (id.isPresent()) {
        	long l = id.get();
        	int i = (int)l;
            Apartment entity;
			try {
				entity = apartmentService.getApartmentById(i);
				model.addAttribute("apartment", entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
        } else {
            model.addAttribute("apartment", new Apartment());
        }
        return "createApartment";
    }
    
    @RequestMapping(path = "/delete/{id}")
    public String deleteApartmentById(Model model, @PathVariable("id") Integer id) throws Exception
    {
        apartmentService.deleteApartmentById(id);
        return "removeApartment";
    }
	
    @RequestMapping(path = "/createApartment", method = RequestMethod.POST)
    public String createOrUpdateApartment(Apartment apartment) throws Exception 
    {
        apartmentService.createOrUpdateApartment(apartment);
        return "redirect:/apartment/showApartments";
    }
	
	/////////////////////////////////////////////////////////////////////////////////
	// Curl Commands below to add, find, and delete
	/////////////////////////////////////////////////////////////////////////////////
	
  @RequestMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String address , @RequestParam Integer Cost) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Apartment n = new Apartment();
    n.setName(name);
    n.setAddress(address);
    n.setCost(Cost);
    
    apartmentRepository.save(n);
    return "Apartment " + n.getId() + " has been added.";
  }

  @PostMapping(path="/find") // Map ONLY POST Requests
  public @ResponseBody String findUser (@RequestParam String address) {
	Apartment n = null;
    Iterable<Apartment> itr = apartmentRepository.findAll();
    String retValue = "Apartments with address " + address + " found in apartments ";
    List<Apartment> apartments = new ArrayList<Apartment>();
    List<Integer> indexes = new ArrayList<Integer>();
    itr.forEach(apartments::add);
    for(int i = 0; i < apartments.size(); i++)
    {
    	n = apartments.get(i);
    	if(n.getAddress().equals(address))
    		indexes.add(n.getId());
    }
    if(indexes.size() == 0)
    	return "Apartment with that address not found";
    for(int j = 0; j < indexes.size(); j++)
    {
    	retValue = retValue.concat(String.valueOf(indexes.get(j) + ", "));
    }
    retValue = retValue.substring(0, retValue.length()-2);
    retValue = retValue.concat(".");
    return retValue;
  }
  
  @PostMapping(path="/remove") // Map ONLY POST Requests
  public @ResponseBody String removeUser(@RequestParam String address) {
	Apartment n = null;
    Iterable<Apartment> itr = apartmentRepository.findAll();
    String retValue = "Apartments with address " + address + " removed at indexes ";
    List<Apartment> apartments = new ArrayList<Apartment>();
    List<Integer> indexes = new ArrayList<Integer>();
    itr.forEach(apartments::add);
    for(int i = 0; i < apartments.size(); i++)
    {
    	n = apartments.get(i);
    	if(n.getAddress().equals(address))
    	{
    		indexes.add(n.getId());
    		apartmentRepository.deleteById(n.getId());
    	}
    }
    if(indexes.size() == 0)
    	return "Apartment with that address not found";
    for(int j = 0; j < indexes.size(); j++)
    {
    	retValue = retValue.concat(String.valueOf(indexes.get(j) + ", "));
    }
    retValue = retValue.substring(0, retValue.length()-2);
    retValue = retValue.concat(".");
    return retValue;
  }
  
  
  
  @GetMapping(path="/all")
  public @ResponseBody Iterable<Apartment> getAllUsers() {
    // This returns a JSON or XML with the users
    return apartmentRepository.findAll();
  }
}