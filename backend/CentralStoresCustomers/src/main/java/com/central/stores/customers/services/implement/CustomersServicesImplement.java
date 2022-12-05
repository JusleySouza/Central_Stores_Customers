package com.central.stores.customers.services.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.central.stores.customers.config.LoggerConfig;
import com.central.stores.customers.crypto.Cryptography;
import com.central.stores.customers.mapper.CustomerMapper;
import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;
import com.central.stores.customers.repository.CustomersRepository;
import com.central.stores.customers.services.CustomersServices;

@Component
public class CustomersServicesImplement implements CustomersServices {
	
	@Autowired
	 private CustomersRepository repository;
	
	@Autowired
	private CustomerMapper mapper;
	
	private Customer customer;
	private ResponseCustomerDTO responseCustomerDTO;

	@Override
	public ResponseEntity<List<Customer>> findAll() {
		List<Customer> listCustomer = repository.findAllByActiveTrue();
		listCustomer.forEach(customer -> customer = Cryptography.decode(customer));
		LoggerConfig.LOGGER_CUSTOMER.info(" Lista de Clientes executada com sucesso!! ");
		return new ResponseEntity<List<Customer>>(listCustomer, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Customer> findByCpf(String cpf) {
		customer = repository.findByCpf(Cryptography.encodeCpf(cpf));
		customer = Cryptography.decode(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Cliente encontrado com sucesso!! ");
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseCustomerDTO> create(RequestCustomerDTO requestCustomerDTO) {
		customer = mapper.toModel(requestCustomerDTO);
		customer = Cryptography.encode(customer);
		repository.save(customer);
		responseCustomerDTO = mapper.modelToResponseCustomerDTO(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Cliente " + customer.getName() + " salvo com sucesso!!");
		return new ResponseEntity<ResponseCustomerDTO>(responseCustomerDTO, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseCustomerDTO> update(RequestCustomerDTO requestCustomerDTO, UUID customerId) {
		customer = repository.findById(customerId).get();
		customer = mapper.updateModel(requestCustomerDTO);
		customer = Cryptography.encode(customer);
		repository.save(customer);
		responseCustomerDTO = mapper.modelToResponseCustomerDTO(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Dados do cliente " + customer.getName() + " salvo com sucesso!!");
		return new ResponseEntity<ResponseCustomerDTO>(responseCustomerDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseCustomerDTO> delete(UUID customerId) {
		customer = repository.findById(customerId).get();
		customer = mapper.customerDelete(customer);
		repository.save(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Cliente " + customer.getName() + " deletado com sucesso!!");
		return new ResponseEntity<ResponseCustomerDTO>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<Customer>> findByNeighborhood(String neighborhood) {
		List<Customer> listCustomers = repository.findAllByActiveTrueAndAddressNeighborhood(neighborhood);
		listCustomers.forEach(customer -> customer = Cryptography.decode(customer));
		LoggerConfig.LOGGER_CUSTOMER.info(" Lista de Clientes por bairro executada com sucesso!! ");
		return new ResponseEntity<List<Customer>>(listCustomers, HttpStatus.OK);
	}
	
}
