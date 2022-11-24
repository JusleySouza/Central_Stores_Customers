package com.central.stores.customers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;
import com.central.stores.customers.services.CustomersServices;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	CustomersServices services;

	@PostMapping
	public ResponseEntity<ResponseCustomerDTO> create(@RequestBody RequestCustomerDTO requestCustomerDTO){
		return services.create(requestCustomerDTO);
	}
	
}
