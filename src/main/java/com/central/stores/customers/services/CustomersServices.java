package com.central.stores.customers.services;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.ListCustomer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;

@Service
public interface CustomersServices {

	public ListCustomer findAll(Integer pageSize, Integer page, String sortBy);

	public ResponseCustomerDTO findByCpf(String cpf);

	public ResponseEntity<Object> create(RequestCustomerDTO requestCustomerDTO);

	public ResponseEntity<Object> update(RequestCustomerDTO requestCustomerDTO, UUID customerId);

	public Customer delete(UUID id);
	
	public ListCustomer findByNeighborhood(String neighborhood, Integer pageSize, Integer page, String sortBy);

}
