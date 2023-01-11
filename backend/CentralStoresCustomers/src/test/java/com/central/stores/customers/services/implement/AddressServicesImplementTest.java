package com.central.stores.customers.services.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.central.stores.customers.exception.ResourceNotFoundException;
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
	private Validator mockValidator;
	
	@Mock
	private AddressMapper mapper;
	
	private Address address;
	private Customer customer;
	private AddressDTO addressDTO;
	private LocalValidatorFactoryBean validator;
	private Set<ConstraintViolation<Object>> violations;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		customer = ClassBuilder.customerBuilder();
		address = ClassBuilder.addressBuilder();
		addressDTO = ClassBuilder.addressDTOBuilder();
		
		validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		
	}

	@Test
	public void create() {
		when(mapper.toModel(any())).thenReturn(address);
		when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
		ResponseEntity<Object>  address = services.create(addressDTO, UUID.randomUUID());
		assertTrue(address.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	@Test
	public void createWithMissingFields() {
		addressDTO.setNumber(null);
		
		violations = validator.validate(addressDTO);
		
		when(mockValidator.validate(any())).thenReturn(violations);
		
		ResponseEntity<Object>  address = services.create(addressDTO, UUID.randomUUID());
		assertTrue(address.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		
	}

	@Test
	public void update() {
		when(addressRepository.findById(any())).thenReturn(Optional.of(address));
		ResponseEntity<Object>  address = services.update(addressDTO, UUID.randomUUID());
		assertTrue(address.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void updateWithMissingFields() {
		addressDTO.setNumber(null);
		
		violations = validator.validate(addressDTO);
		
		when(mockValidator.validate(any())).thenReturn(violations);
		
		ResponseEntity<Object>  address = services.update(addressDTO, UUID.randomUUID());
		assertTrue(address.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		
	}
	
	@Test
	public void updateResourceNotFoundException() {
		String messageError = "No records found for this id!!";
		
		when(addressRepository.findById(any())).thenReturn(Optional.ofNullable(null));
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.update(addressDTO, UUID.randomUUID());
		}).getMessage();
		
		assertEquals(messageError, message);
		
	}
	
}
