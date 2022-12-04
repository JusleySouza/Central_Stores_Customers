package com.central.stores.customers.services.implement;

import java.util.Date;
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
	public ResponseEntity<Address> create(AddressDTO requestAddressDTO, UUID customerId) {
		address = new Address();
		customer = new Customer();
		address.transformRequestAddressDTOToModel(requestAddressDTO);
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
		address = updateModel(address, requestAddressDTO);
		addressRepository.save(address);
		LoggerConfig.LOGGER_ADDRESS.info("Endereço atualizado com sucesso!!");
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}
	
	private Address updateModel(Address address, AddressDTO requestAddressDTO) {
		address.setStreet(requestAddressDTO.getStreet());
		address.setNumber(requestAddressDTO.getNumber());
		address.setNeighborhood(requestAddressDTO.getNeighborhood());
		address.setCity(requestAddressDTO.getCity());
		address.setChanged(new Date());
		return address;
	}

}
