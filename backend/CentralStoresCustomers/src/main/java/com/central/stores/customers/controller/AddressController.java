package com.central.stores.customers.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.dto.AddressDTO;
import com.central.stores.customers.services.AddressServices;

import lombok.Generated;

@RestController
@RequestMapping("/customers/{customerId}/address")
public class AddressController {
	
	@Autowired
	private AddressServices services;
	
	@PostMapping
	public ResponseEntity<Address> create(@RequestBody AddressDTO requestAddressDTO, @PathVariable("customerId") UUID customerId) {
		return new ResponseEntity<Address>(services.create(requestAddressDTO, customerId), HttpStatus.CREATED); 
	}
	
	@PutMapping("/{addressId}")
	public ResponseEntity<Address> update(@RequestBody AddressDTO requestAddressDTO,  
			@PathVariable("addressId") UUID addressId){
		return new ResponseEntity<Address>(services.update(requestAddressDTO, addressId), HttpStatus.OK);
	}

}
