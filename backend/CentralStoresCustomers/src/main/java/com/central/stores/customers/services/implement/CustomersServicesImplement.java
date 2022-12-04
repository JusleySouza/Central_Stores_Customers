package com.central.stores.customers.services.implement;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.central.stores.customers.config.LoggerConfig;
import com.central.stores.customers.crypto.Cryptography;
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
		customer = new Customer();
		responseCustomerDTO = new ResponseCustomerDTO();
		customer.transformRequestCustomerDTOToModel(requestCustomerDTO);
		customer = Cryptography.encode(customer);
		repository.save(customer);
		responseCustomerDTO.transformModelToResponseCustomerDTO(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Cliente " + customer.getName() + " salvo com sucesso!!");
		return new ResponseEntity<ResponseCustomerDTO>(responseCustomerDTO, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseCustomerDTO> update(RequestCustomerDTO requestCustomerDTO, UUID customerId) {
		responseCustomerDTO = new ResponseCustomerDTO();
		customer = repository.findById(customerId).get();
		customer = updateModel(customer, requestCustomerDTO);
		customer = Cryptography.encode(customer);
		repository.save(customer);
		responseCustomerDTO.transformModelToResponseCustomerDTO(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Dados do cliente " + customer.getName() + " salvo com sucesso!!");
		return new ResponseEntity<ResponseCustomerDTO>(responseCustomerDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseCustomerDTO> delete(UUID customerId) {
		customer = repository.findById(customerId).get();
		customer.setActive(Boolean.FALSE);
		customer.setChanged(new Date());
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
	
	private Customer updateModel(Customer customer, RequestCustomerDTO requestCustomerDTO) {
		customer.setName(requestCustomerDTO.getName());
		customer.setCpf(requestCustomerDTO.getCpf());
		customer.setRg(requestCustomerDTO.getRg());
		customer.setGender(requestCustomerDTO.getGender());
		customer.setPhone(requestCustomerDTO.getPhone());
		customer.setEmail(requestCustomerDTO.getEmail());
		customer.setChanged(new Date());
		return customer;
	}
	
}
