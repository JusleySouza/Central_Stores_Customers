package com.central.stores.customers.services.implement;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.central.stores.customers.config.LoggerConfig;
import com.central.stores.customers.crypto.Cryptography;
import com.central.stores.customers.exception.ResourceNotFoundException;
import com.central.stores.customers.mapper.CustomerMapper;
import com.central.stores.customers.mapper.UpdateModel;
import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;
import com.central.stores.customers.model.dto.error.ResponseError;
import com.central.stores.customers.repository.CustomersRepository;
import com.central.stores.customers.services.CustomersServices;

@Component
public class CustomersServicesImplement implements CustomersServices {
	
	@Autowired
	 private CustomersRepository repository;
	
	@Autowired
	private CustomerMapper mapper;
	
	@Autowired
	private Validator validator;
	
	private Customer customer;
	private ResponseCustomerDTO responseCustomerDTO;

	@Override
	@Cacheable(cacheNames = "Customers", key = "#root.method.name")
	public List<Customer> findAll() {
		List<Customer> listCustomer = repository.findAllByActiveTrue();
		listCustomer.forEach(customer -> customer = Cryptography.decode(customer));
		LoggerConfig.LOGGER_CUSTOMER.info(" Customer List successfully executed!! ");
		return listCustomer;
	}

	@Override
	@Cacheable(cacheNames = "Customers", key = "#cpf")
	public Customer findByCpf(String cpf) {
		customer = repository.findByCpf(Cryptography.encodeCpf(cpf));
		
		if(customer == null) {
			throw new ResourceNotFoundException("No records found for this cpf!!");
		}
		
		customer = Cryptography.decode(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Customer found successfully!! ");
		return customer;
	}

	@Override
	public ResponseEntity<Object> create(RequestCustomerDTO requestCustomerDTO) {
		
		Set<ConstraintViolation<RequestCustomerDTO>> violations = validator.validate(requestCustomerDTO);
		
		if(!violations.isEmpty()) {
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		customer = mapper.toModel(requestCustomerDTO);
		customer = Cryptography.encode(customer);
		repository.save(customer);
		responseCustomerDTO = mapper.modelToResponseCustomerDTO(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Customer " + customer.getName() + " saved successfully!!");
		return new ResponseEntity<Object>(responseCustomerDTO, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Object> update(RequestCustomerDTO requestCustomerDTO, UUID customerId) {
		
		Set<ConstraintViolation<RequestCustomerDTO>> violations = validator.validate(requestCustomerDTO);
		
		if(!violations.isEmpty()) {
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		customer = repository.findById(customerId).orElseThrow(() ->
		new ResourceNotFoundException("No records found for this id!!")
		);
		
		customer = UpdateModel.customer(customer, requestCustomerDTO);
		customer = Cryptography.encode(customer);
		repository.save(customer);
		
		responseCustomerDTO = mapper.modelToResponseCustomerDTO(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Customer data " + customer.getName() + " saved successfully!!");
		return new ResponseEntity<Object>(responseCustomerDTO, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Object> delete(UUID customerId) {
		customer = repository.findById(customerId).get();
		customer = mapper.customerDelete(customer);
		repository.save(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Customer " + customer.getName() + " deleted successfully!!");
		return new ResponseEntity<Object>(customer, HttpStatus.NO_CONTENT);
	}

	@Override
	@Cacheable(cacheNames = "Customers", key = "#neighborhood")
	public List<Customer> findByNeighborhood(String neighborhood) {
		List<Customer> listCustomers = repository.findAllByActiveTrueAndAddressNeighborhood(neighborhood);
		
		if(listCustomers.isEmpty()) {
			throw new ResourceNotFoundException("No records found for this neighborhood!!");
		}
		
		listCustomers.forEach(customer -> customer = Cryptography.decode(customer));
		LoggerConfig.LOGGER_CUSTOMER.info(" List of Customers by neighborhood successfully executed!! ");
		return listCustomers;
	}
	
}
