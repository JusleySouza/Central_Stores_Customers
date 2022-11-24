package com.central.stores.customers.services.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;
import com.central.stores.customers.repository.CustomersRepository;
import com.central.stores.customers.services.CustomersServices;

@Component
public class CustomersServicesImplement implements CustomersServices {
	
	@Autowired
	CustomersRepository repository;
	
	Customer customer;
	ResponseCustomerDTO responseCustomerDTO;

	@Override
	public ResponseEntity<List<Customer>> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Customer> findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseCustomerDTO> create(RequestCustomerDTO requestCustomerDTO) {
		customer = new Customer();
		responseCustomerDTO = new ResponseCustomerDTO();
		
		customer.transformRequestCustomerDTOToModel(requestCustomerDTO);
		
		repository.save(customer);
		
		responseCustomerDTO.transformModelToResponseCustomerDTO(customer);
		
		return new ResponseEntity<ResponseCustomerDTO>(responseCustomerDTO, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Customer> update(RequestCustomerDTO requestCustomerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Customer> delete(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
