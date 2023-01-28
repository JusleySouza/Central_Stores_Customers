package com.central.stores.customers.controller;

import java.util.UUID;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.stores.customers.model.dto.ListCustomer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;
import com.central.stores.customers.services.CustomersServices;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomersServices services;
	
	

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody RequestCustomerDTO requestCustomerDTO) {
		return services.create(requestCustomerDTO);
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<Object> update(@RequestBody RequestCustomerDTO requestCustomerDTO,
			@PathVariable("customerId") UUID customerId) {
		return services.update(requestCustomerDTO, customerId);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Object> delete(@PathVariable("customerId") UUID customerId){
		services.delete(customerId);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("list")
	public ResponseEntity<ListCustomer> listCustomers(
			@Min(value=1, message = "Tamanho mínimo 1.")
			@RequestParam(defaultValue = "10" , value="pageSize", required = false) Integer pageSize, 
			@Min(value=0, message = "Tamanho mínimo 0.")
			@RequestParam(defaultValue = "0" , value="page", required = false) Integer page, 
			@RequestParam(defaultValue = "name, DESC" , value="sortBy", required = false) String sortBy
			){
		return new ResponseEntity<ListCustomer>(services.findAll(pageSize, page, sortBy), HttpStatus.OK);
	}
	
	@GetMapping("/{customerCpf}")
	public ResponseEntity<ResponseCustomerDTO> findByCpf(@PathVariable("customerCpf") String customerCpf){
		return new ResponseEntity<ResponseCustomerDTO>(services.findByCpf(customerCpf), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<ListCustomer> findByNeighborhood(@RequestParam("neighborhood") String neighborhood,
			@Min(value=1, message = "Tamanho mínimo 1.")
			@RequestParam(defaultValue = "10" , value="pageSize", required = false) Integer pageSize, 
			@Min(value=0, message = "Tamanho mínimo 0.")
			@RequestParam(defaultValue = "0" , value="page", required = false) Integer page, 
			@RequestParam(defaultValue = "name, DESC" , value="sortBy", required = false) String sortBy
			){
		return new ResponseEntity<ListCustomer>(services.findByNeighborhood(neighborhood, pageSize, page, sortBy), HttpStatus.OK);
	}
}
