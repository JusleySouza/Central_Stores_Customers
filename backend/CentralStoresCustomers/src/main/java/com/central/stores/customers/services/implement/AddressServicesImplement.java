package com.central.stores.customers.services.implement;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.central.stores.customers.config.LoggerConfig;
import com.central.stores.customers.mapper.AddressMapper;
import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.AddressDTO;
import com.central.stores.customers.repository.AddressRepository;
import com.central.stores.customers.repository.CustomersRepository;
import com.central.stores.customers.services.AddressServices;

@Component
public class AddressServicesImplement implements AddressServices {
	
	@Autowired
	private CustomersRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressMapper mapper;
	
	private Address address;
	private Customer customer;

	@Override
	public ResponseEntity<Address> create(AddressDTO requestAddressDTO, UUID customerId) {
		address = mapper.toModel(requestAddressDTO);
		addressRepository.save(address);
		customer = customerRepository.findById(customerId).get();
		customer.setAddress(address);
		customerRepository.save(customer);
		LoggerConfig.LOGGER_ADDRESS.info("Endereço do cliente " + customer.getName() + " salvo com sucesso!!");
		return new ResponseEntity<Address>(address, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Address> update(AddressDTO requestAddressDTO, UUID addressId) {
		address = addressRepository.findById(addressId).get();
		address = mapper.updateModel(requestAddressDTO);
		addressRepository.save(address);
		LoggerConfig.LOGGER_ADDRESS.info("Endereço atualizado com sucesso!!");
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}

}
