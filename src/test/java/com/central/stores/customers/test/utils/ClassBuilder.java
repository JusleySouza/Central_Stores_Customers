package com.central.stores.customers.test.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.AddressDTO;
import com.central.stores.customers.model.dto.ListCustomer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;
import com.central.stores.customers.model.dto.ResponseSumarizedCustomerDTO;
import com.central.stores.customers.pagination.PaginationDTO;

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

	public static AddressDTO addressDTOBuilder() {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setCity("Bauru");
		addressDTO.setNeighborhood("Pedro Santos Drumond");
		addressDTO.setNumber(56);
		addressDTO.setStreet("Rua das Amelias");
		return addressDTO;
	}

	public static Customer customerBuilder() {
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
	
	public static RequestCustomerDTO requestCustomerDTOBuilder() {
		 RequestCustomerDTO requestCustomerDTO = new RequestCustomerDTO();
		 requestCustomerDTO.setCpf("12365478965");
		 requestCustomerDTO.setEmail("caio@castro.com");
		 requestCustomerDTO.setGender("masculino");
		 requestCustomerDTO.setName("Caio Castro");
		 requestCustomerDTO.setPhone("1111111111");
		 requestCustomerDTO.setRg("325698741");
		 return requestCustomerDTO;
	}
	
	public static ResponseSumarizedCustomerDTO responseSumarizedCustomerDTOBuilder() {
		 ResponseSumarizedCustomerDTO responseSumarizedCustomerDTO = new ResponseSumarizedCustomerDTO();
		 responseSumarizedCustomerDTO.setName("Caio Castro");
		 responseSumarizedCustomerDTO.setId(UUID.randomUUID());
		 return responseSumarizedCustomerDTO;
	}
	
	public static ResponseCustomerDTO responseCustomerDTOBuilder() {
		ResponseCustomerDTO responseCustomerDTO = new ResponseCustomerDTO();
		responseCustomerDTO.setId(UUID.randomUUID());
		responseCustomerDTO.setName("Caio Castro");
		responseCustomerDTO.setCpf("12365478965");
		responseCustomerDTO.setRg("325698741");
		responseCustomerDTO.setGender("masculino");
		responseCustomerDTO.setPhone("1111111111");
		responseCustomerDTO.setEmail("caio@castro.com");
		responseCustomerDTO.setAddress(addressDTOBuilder());
		return responseCustomerDTO;
	}
	
	public static PaginationDTO paginationBuilder() {
		PaginationDTO pagination = new PaginationDTO();
		pagination.setOffset(0);
		pagination.setPageSize(4);
		pagination.setPageNumber(1);
		pagination.setMoreElements(true);
		pagination.setTotalPages(6);
	    pagination.setTotalElements(2);
		return pagination;
	}
	
	public static ListCustomer listCustomerBuilder() {
		ListCustomer listCustomer = new ListCustomer();
		listCustomer.setPageable(paginationBuilder());
		List<ResponseCustomerDTO> listCustomerDTO = List.of(responseCustomerDTOBuilder());
		listCustomer.setContent(listCustomerDTO);
		return listCustomer;
	}
	
}
