package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Payment {
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Integer pID;
	  private String name;
	  private String address;
	  private Integer amount;
	  private String date;
	  private Integer aID;

	  
	  public String getAddress()
	  {
		  return address;
	  }
	  
	  public void setAddress(String address)
	  {
		 this.address = address;
	  }
	  
	  public String getDate()
	  {
		  return date;
	  }
	  
	  public void setDate(String date)
	  {
		  this.date = date;
	  }
	  
	  public Integer getAID() {
		  return aID;
	  }
	  
	  public void setAID(Integer aID) {
		  this.aID = aID;
	  }

	  public Integer getId() {
	    return pID;
	  }

	  public void setId(Integer id) {
	    this.pID = id;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public Integer getAmount() {
	    return amount;
	  }

	  public void setAmount(Integer amount) {
	    this.amount = amount;
	  }
}
