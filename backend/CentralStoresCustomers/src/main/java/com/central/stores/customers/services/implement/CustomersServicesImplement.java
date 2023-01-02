package com.central.stores.customers.services.implement;

import java.util.List;
import java.util.UUID;

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
	@Cacheable(cacheNames = "Customers", key = "#root.method.name")
	public List<Customer> findAll() {
		List<Customer> listCustomer = repository.findAllByActiveTrue();
		listCustomer.forEach(customer -> customer = Cryptography.decode(customer));
		LoggerConfig.LOGGER_CUSTOMER.info(" Lista de Clientes executada com sucesso!! ");
		return listCustomer;
	}

	@Override
	@Cacheable(cacheNames = "Customers", key = "#cpf")
	public Customer findByCpf(String cpf) {
		customer = repository.findByCpf(Cryptography.encodeCpf(cpf));
		
		if(customer == null) {
			throw new ResourceNotFoundException("Nenhum registro encontrado para este cpf!!");
		}
		
		customer = Cryptography.decode(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Cliente encontrado com sucesso!! ");
		return customer;
	}

	@Override
	public ResponseCustomerDTO create(RequestCustomerDTO requestCustomerDTO) {
		customer = mapper.toModel(requestCustomerDTO);
		customer = Cryptography.encode(customer);
		repository.save(customer);
		responseCustomerDTO = mapper.modelToResponseCustomerDTO(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Cliente " + customer.getName() + " salvo com sucesso!!");
		return responseCustomerDTO;
	}

	@Override
	public ResponseCustomerDTO update(RequestCustomerDTO requestCustomerDTO, UUID customerId) {
		customer = repository.findById(customerId).orElseThrow(() ->
		new ResourceNotFoundException("Nenhum registro encontrado para este id!!")
		);
		
		customer = UpdateModel.customer(customer, requestCustomerDTO);
		customer = Cryptography.encode(customer);
		repository.save(customer);
		
		responseCustomerDTO = mapper.modelToResponseCustomerDTO(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Dados do cliente " + customer.getName() + " salvo com sucesso!!");
		return responseCustomerDTO;
	}

	@Override
	public Customer delete(UUID customerId) {
		customer = repository.findById(customerId).get();
		customer = mapper.customerDelete(customer);
		repository.save(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Cliente " + customer.getName() + " deletado com sucesso!!");
		return customer;
	}

	@Override
	@Cacheable(cacheNames = "Customers", key = "#neighborhood")
	public List<Customer> findByNeighborhood(String neighborhood) {
		List<Customer> listCustomers = repository.findAllByActiveTrueAndAddressNeighborhood(neighborhood);
		
		if(listCustomers.isEmpty()) {
			throw new ResourceNotFoundException("Nenhum registro encontrado para este bairro!!");
		}
		
		listCustomers.forEach(customer -> customer = Cryptography.decode(customer));
		LoggerConfig.LOGGER_CUSTOMER.info(" Lista de Clientes por bairro executada com sucesso!! ");
		return listCustomers;
	}
	
}
