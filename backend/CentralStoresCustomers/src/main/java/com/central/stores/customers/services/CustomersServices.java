package com.central.stores.customers.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;

@Service
public interface CustomersServices {

	public List<Customer> findAll();

	public Customer findByCpf(String cpf);

	public ResponseCustomerDTO create(RequestCustomerDTO requestCustomerDTO);

	public ResponseCustomerDTO update(RequestCustomerDTO requestCustomerDTO, UUID customerId);

	public Customer delete(UUID id);
	
	public List<Customer> findByNeighborhood(String neighborhood);

}
