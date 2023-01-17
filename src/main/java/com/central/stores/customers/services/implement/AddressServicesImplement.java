package com.central.stores.customers.services.implement;

import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.central.stores.customers.config.LoggerConfig;
import com.central.stores.customers.exception.ResourceNotFoundException;
import com.central.stores.customers.mapper.AddressMapper;
import com.central.stores.customers.mapper.UpdateModel;
import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.AddressDTO;
import com.central.stores.customers.model.dto.error.ResponseError;
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
	
	@Autowired
	private Validator validator;

	private Address address;
	private Customer customer;

	@Override
	public ResponseEntity<Object> create(AddressDTO requestAddressDTO, UUID customerId) {
		
		Set<ConstraintViolation<AddressDTO>> violations = validator.validate(requestAddressDTO);

		if (!violations.isEmpty()) {
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		address = mapper.toModel(requestAddressDTO);
		addressRepository.save(address);
		customer = customerRepository.findById(customerId).get();
		customer.setAddress(address);
		customerRepository.save(customer);
		LoggerConfig.LOGGER_ADDRESS.info("Customer address " + customer.getName() + " saved successfully!!");
		return new ResponseEntity<Object>(address, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Object> update(AddressDTO requestAddressDTO, UUID addressId) {
		
		Set<ConstraintViolation<AddressDTO>> violations = validator.validate(requestAddressDTO);

		if (!violations.isEmpty()) {
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		address = addressRepository.findById(addressId).orElseThrow(() ->
		new ResourceNotFoundException("No records found for this id!!")
		);
		address = UpdateModel.address(address, requestAddressDTO);
		addressRepository.save(address);
		LoggerConfig.LOGGER_ADDRESS.info("Address successfully updated!!");
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	
	}

}
