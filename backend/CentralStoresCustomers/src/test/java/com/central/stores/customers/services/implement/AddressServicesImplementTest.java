package com.central.stores.customers.services.implement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.central.stores.customers.mapper.AddressMapper;
import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.AddressDTO;
import com.central.stores.customers.repository.AddressRepository;
import com.central.stores.customers.repository.CustomersRepository;
import com.central.stores.customers.test.utils.ClassBuilder;

class AddressServicesImplementTest {
	
	@InjectMocks
	private AddressServicesImplement services;
	
	@Mock
	private CustomersRepository customerRepository;
	
	@Mock
	private AddressRepository addressRepository;
	
	@Mock
	private AddressMapper mapper;
	
	private Address address;
	private Customer customer;
	private AddressDTO addressDTO;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		customer = ClassBuilder.customerBuider();
		address = ClassBuilder.addressBuilder();
		addressDTO = ClassBuilder.addressDTOBuider();
		
	}

	@Test
	public void create() {
		when(mapper.toModel(any())).thenReturn(address);
		when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
		ResponseEntity<Address> address = services.create(addressDTO, UUID.randomUUID());
		assertNotNull(address);
	}

	@Test
	public void update() {
		when(addressRepository.findById(any())).thenReturn(Optional.of(address));
		ResponseEntity<Address> address = services.update(addressDTO, UUID.randomUUID());
		assertNotNull(address);
	}
	
}
