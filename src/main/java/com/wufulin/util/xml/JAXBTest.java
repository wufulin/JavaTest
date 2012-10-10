package com.wufulin.util.xml;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.wufulin.util.xml.Person.Address;
import com.wufulin.util.xml.Person.Gender;

public class JAXBTest {

	/**
	 * @param args
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws JAXBException, IOException {
		
		JAXBContext context=JAXBContext.newInstance(Person.class);
		
		// Java Object -> Xml
		Marshaller marshaller=context.createMarshaller();
		Address address=new Address("China","Beijing","Beijing","ShangDi West","100080");
		Person p=new Person(Calendar.getInstance(), "JAXB2", address, Gender.MALE, "SW");
		FileWriter fw=new FileWriter("person.xml");
		marshaller.marshal(p, fw);

		// Xml -> Java Object
		FileReader fr=new FileReader("person.xml");
		Unmarshaller unmarshaller=context.createUnmarshaller();
		Person person=(Person)unmarshaller.unmarshal(fr);
		System.out.println(person.getAddress().getCountry());
	}

}
