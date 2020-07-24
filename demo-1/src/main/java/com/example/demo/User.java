package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Integer uID;
	  private String name;
	  private String birthday;
	  private Integer aID;

	  public Integer getAID() {
		  return aID;
	  }
	  
	  public void setAID(Integer aID) {
		  this.aID = aID;
	  }

	  public Integer getId() {
	    return uID;
	  }

	  public void setId(Integer id) {
	    this.uID = id;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getBirthday() {
	    return birthday;
	  }

	  public void setBirthday(String birthday) {
	    this.birthday = birthday;
	  }
}
