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

	public ResponseEntity<List<Customer>> findAll();

	public ResponseEntity<Customer> findById(UUID id);

	public ResponseEntity<ResponseCustomerDTO> create(RequestCustomerDTO requestCustomerDTO);

	public ResponseEntity<ResponseCustomerDTO> update(RequestCustomerDTO requestCustomerDTO, UUID customerId);

	public ResponseEntity<ResponseCustomerDTO> delete(UUID id);

}
