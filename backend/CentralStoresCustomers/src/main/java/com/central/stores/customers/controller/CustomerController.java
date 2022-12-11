package com.central.stores.customers.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;
import com.central.stores.customers.services.CustomersServices;

import lombok.Generated;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomersServices services;
	
	

	@PostMapping
	public ResponseEntity<ResponseCustomerDTO> create(@RequestBody RequestCustomerDTO requestCustomerDTO) {
		return services.create(requestCustomerDTO);
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<ResponseCustomerDTO> update(@RequestBody RequestCustomerDTO requestCustomerDTO,
			@PathVariable("customerId") UUID customerId) {
		return services.update(requestCustomerDTO, customerId);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<ResponseCustomerDTO> delete(@PathVariable("customerId") UUID customerId){
		return services.delete(customerId);
	}
	
	@GetMapping("list")
	public ResponseEntity<List<Customer>> listCustomers(){
		return services.findAll();
	}
	
	@GetMapping("/{customerCpf}")
	public ResponseEntity<Customer> findByCpf(@PathVariable("customerCpf") String customerCpf){
		return services.findByCpf(customerCpf);
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> findByNeighborhood(@RequestParam("neighborhood") String neighborhood){
		return services.findByNeighborhood(neighborhood);
	}
}
