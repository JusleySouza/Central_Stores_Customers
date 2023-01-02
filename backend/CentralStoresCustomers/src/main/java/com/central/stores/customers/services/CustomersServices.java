package com.central.stores.customers.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;

@Service
public interface CustomersServices {

	public List<Customer> findAll();

	public Customer findByCpf(String cpf);

	public ResponseEntity<Object> create(RequestCustomerDTO requestCustomerDTO);

	public ResponseEntity<Object> update(RequestCustomerDTO requestCustomerDTO, UUID customerId);

	public ResponseEntity<Object> delete(UUID id);
	
	public List<Customer> findByNeighborhood(String neighborhood);

}
