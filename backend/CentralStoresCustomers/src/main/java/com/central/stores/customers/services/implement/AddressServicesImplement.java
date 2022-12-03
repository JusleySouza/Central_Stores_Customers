package com.central.stores.customers.services.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.central.stores.customers.config.LoggerConfig;
import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.AddressDTO;
import com.central.stores.customers.repository.AddressRepository;
import com.central.stores.customers.repository.CustomersRepository;
import com.central.stores.customers.services.AddressServices;

@Component
public class AddressServicesImplement implements AddressServices {
	
	@Autowired
	CustomersRepository customerRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	Address address;
	Customer customer;

	@Override
	public ResponseEntity<List<Address>> findAll() {
		return null;
	}

	@Override
	public ResponseEntity<Address> findById(UUID id) {
		return null;
	}

	@Override
	public ResponseEntity<AddressDTO> create(AddressDTO requestAddressDTO, UUID customerId) {
		address = new Address();
		customer = new Customer();
		address.transformRequestAddressDTOToModel(requestAddressDTO);
		addressRepository.save(address);
		customer = customerRepository.findById(customerId).get();
		customer.setAddress(address);
		customerRepository.save(customer);
		LoggerConfig.LOGGER_ADDRESS.info("Endereço do cliente " + customer.getName() + " salvo com sucesso!!");
		return new ResponseEntity<AddressDTO>(HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Address> update(AddressDTO requestAddressDTO) {

		return null;
	}

	@Override
	public ResponseEntity<Address> delete(UUID id) {
		return null;
	}

}
