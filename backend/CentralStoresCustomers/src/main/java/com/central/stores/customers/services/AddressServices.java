package com.central.stores.customers.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.dto.AddressDTO;

@Service
public interface AddressServices {

	public ResponseEntity<List<Address>> findAll();

	public ResponseEntity<Address> findById(UUID id);

	public ResponseEntity<AddressDTO> create(AddressDTO requestAddressDTO, UUID customerId);

	public ResponseEntity<Address> update(AddressDTO requestAddressDTO);

	public ResponseEntity<Address> delete(UUID id);
	
}
