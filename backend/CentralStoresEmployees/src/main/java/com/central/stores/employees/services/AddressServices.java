package com.central.stores.employees.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.central.stores.employees.model.dto.AddressDTO;

@Service
public interface AddressServices {
	public ResponseEntity<List<AddressDTO>> findAll();
	
	public ResponseEntity<AddressDTO> findById(UUID id);
	
	public ResponseEntity<AddressDTO> create(AddressDTO requestAddressDTO, UUID employeeId);
	
	public ResponseEntity<AddressDTO> update(AddressDTO requestAddressDTO);
	
	public ResponseEntity<AddressDTO> delete(UUID id);
}
