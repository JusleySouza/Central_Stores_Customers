package com.central.stores.customers.services;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.dto.AddressDTO;

@Service
public interface AddressServices {

	public ResponseEntity<Object> create(AddressDTO requestAddressDTO, UUID customerId);

	public ResponseEntity<Object> update(AddressDTO requestAddressDTO, UUID addressId );
	
}
