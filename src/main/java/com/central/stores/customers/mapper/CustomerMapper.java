package com.central.stores.customers.mapper;

import java.time.LocalDate;

import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;

public final class CustomerMapper {

	public static Customer toModel(RequestCustomerDTO requestCustomerDTO) {
		return Customer.builder()
				.name(requestCustomerDTO.getName())
				.cpf(requestCustomerDTO.getCpf())
				.rg(requestCustomerDTO.getRg())
				.gender(requestCustomerDTO.getGender())
				.phone(requestCustomerDTO.getPhone())
				.email(requestCustomerDTO.getEmail())
				.created(LocalDate.now())
				.active(Boolean.TRUE)
				.build();
	}
	
	public static Customer customerDelete(Customer customer) {
		customer.setActive(Boolean.FALSE);
		customer.setChanged(LocalDate.now());
		return customer;
	}
	
	public static ResponseCustomerDTO modelToResponseCustomerDTO(Customer customer) {
		return ResponseCustomerDTO.builder()
				.id(customer.getId())
				.name(customer.getName())
				.build();
	}
	
	public static Customer updateCustomer(Customer customer, RequestCustomerDTO RequestCustomerDTO) {
		customer.setName(RequestCustomerDTO.getName());
		customer.setCpf(RequestCustomerDTO.getCpf());
		customer.setRg(RequestCustomerDTO.getRg());
		customer.setGender(RequestCustomerDTO.getGender());
		customer.setPhone(RequestCustomerDTO.getPhone());
		customer.setEmail(RequestCustomerDTO.getEmail());
		customer.setChanged(LocalDate.now());
		return customer;
	}
	
}
