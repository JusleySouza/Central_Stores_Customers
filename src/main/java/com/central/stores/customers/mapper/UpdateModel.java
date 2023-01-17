package com.central.stores.customers.mapper;

import java.time.LocalDate;

import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.AddressDTO;
import com.central.stores.customers.model.dto.RequestCustomerDTO;

import lombok.Generated;

@Generated
public final class UpdateModel {

	public static Customer customer(Customer customer, RequestCustomerDTO RequestCustomerDTO) {
		customer.setName(RequestCustomerDTO.getName());
		customer.setCpf(RequestCustomerDTO.getCpf());
		customer.setRg(RequestCustomerDTO.getRg());
		customer.setGender(RequestCustomerDTO.getGender());
		customer.setPhone(RequestCustomerDTO.getPhone());
		customer.setEmail(RequestCustomerDTO.getEmail());
		customer.setChanged(LocalDate.now());
		return customer;
	}
	
	public static Address address(Address address, AddressDTO RequestAddressDTO) {
		address.setStreet(RequestAddressDTO.getStreet());
		address.setNumber(RequestAddressDTO.getNumber());
		address.setNeighborhood(RequestAddressDTO.getNeighborhood());
		address.setCity(RequestAddressDTO.getCity());
		address.setChanged(LocalDate.now());
		return address;
	}
	
}
