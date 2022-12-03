package com.central.stores.customers.services.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.dto.AddressDTO;
import com.central.stores.customers.services.AddressServices;

@Component
public class AddressServicesImplement implements AddressServices {

	@Override
	public ResponseEntity<List<Address>> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Address> findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<AddressDTO> create(AddressDTO requestAddressDTO, UUID customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Address> update(AddressDTO requestAddressDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Address> delete(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
