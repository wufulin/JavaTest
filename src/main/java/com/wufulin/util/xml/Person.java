package com.wufulin.util.xml;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
	
	@XmlAttribute private String name;
	@XmlElement private Calendar birthday;
	@XmlElement private Address address;
	@XmlElement private Gender gender;
	@XmlElement private String job;
	
	static class Address{
		@XmlAttribute private String country;
		@XmlElement private String state;
		@XmlElement private String city;
		@XmlElement private String street;
		@SuppressWarnings("unused")private String zipcode;
		
		public Address(){}
		
		public Address(String country,String state,String city,String street,String zipcode){
			this.country=country;
			this.state=state;
			this.city=city;
			this.street=street;
			this.zipcode=zipcode;
		}
		
		public String getCountry(){
			return country;
		}
	}

	public enum Gender{
		MALE(true),
		FEMALE(false);
		@SuppressWarnings("unused")
		private boolean value;
		Gender(boolean _value){
			this.value=_value;
		}
	}

	public Person(){};
	
	public Person(Calendar birthDay, String name, Address address, Gender gender, String job) {
        this.birthday = birthDay;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.job = job;
    }

	
	public Address getAddress() {
		return address;
	}
	
}
