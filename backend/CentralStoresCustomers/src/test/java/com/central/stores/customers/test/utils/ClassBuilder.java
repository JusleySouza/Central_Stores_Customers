package com.central.stores.customers.test.utils;

import java.time.LocalDate;
import java.util.UUID;

import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.AddressDTO;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;

public final class ClassBuilder {

	public static Address addressBuilder() {
		Address address = new Address();
		address.setCity("Bauru");
		address.setNeighborhood("Pedro Santos Drumond");
		address.setNumber(56);
		address.setStreet("Rua das Amelias");
		address.setChanged(LocalDate.now());
		address.setCreated(LocalDate.now());
		address.setId(UUID.randomUUID());
		return address;
	}

	public static AddressDTO addressDTOBuider() {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setCity("Bauru");
		addressDTO.setNeighborhood("Pedro Santos Drumond");
		addressDTO.setNumber(56);
		addressDTO.setStreet("Rua das Amelias");
		return addressDTO;
	}

	public static Customer customerBuider() {
		 Customer customer = new Customer();
		 customer.setActive(true);
		 customer.setChanged(LocalDate.now());
		 customer.setCpf("12365478965");
		 customer.setCreated(LocalDate.now());
		 customer.setEmail("caio@castro.com");
		 customer.setGender("masculino");
		 customer.setName("Caio Castro");
		 customer.setPhone("1111111111");
		 customer.setRg("325698741");
		 customer.setId(UUID.randomUUID());
		 return customer;
	}
	
	public static RequestCustomerDTO requestCustomerDTOBuider() {
		 RequestCustomerDTO requestCustomerDTO = new RequestCustomerDTO();
		 requestCustomerDTO.setCpf("12365478965");
		 requestCustomerDTO.setEmail("caio@castro.com");
		 requestCustomerDTO.setGender("masculino");
		 requestCustomerDTO.setName("Caio Castro");
		 requestCustomerDTO.setPhone("1111111111");
		 requestCustomerDTO.setRg("325698741");
		 return requestCustomerDTO;
	}
	
	public static ResponseCustomerDTO responseCustomerDTOBuider() {
		 ResponseCustomerDTO responseCustomerDTO = new ResponseCustomerDTO();
		 responseCustomerDTO.setName("Caio Castro");
		 responseCustomerDTO.setId(UUID.randomUUID());
		 return responseCustomerDTO;
	}
	
}
