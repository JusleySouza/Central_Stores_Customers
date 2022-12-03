package com.central.stores.employees.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.central.stores.employees.model.Address;
import com.central.stores.employees.model.dto.AddressDTO;

@Service
public interface AddressServices {
	public ResponseEntity<List<AddressDTO>> findAll();
	
	public ResponseEntity<AddressDTO> findById(UUID addressId);
	
	public ResponseEntity<Address> create(AddressDTO requestAddressDTO, UUID employeeId);
	
	public ResponseEntity<Address> update(AddressDTO requestAddressDTO, UUID addressId);
	
	public ResponseEntity<AddressDTO> delete(UUID addressId);
}
