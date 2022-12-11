package com.central.stores.customers.services.implement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
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
import com.central.stores.customers.model.dto.ResponseCustomerDTO;
import com.central.stores.customers.repository.AddressRepository;
import com.central.stores.customers.repository.CustomersRepository;

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
		customer = new Customer();
		address = new Address();
		addressDTO = new AddressDTO();
		
		customer.setActive(true);
		customer.setChanged(LocalDate.now());
		customer.setCpf("12365478965");
		customer.setCreated(LocalDate.now());
		customer.setEmail("caio@castro.com");
		customer.setGender("masculino");
		customer.setName("Caio Castro");
		customer.setPhone("1111111111");
		customer.setRg("325698741");
		
		address.setId(UUID.randomUUID());
		address.setCity("Bauru");
		address.setNeighborhood("Pedro Santos Drumond");
		address.setNumber(56);
		address.setStreet("Rua das Amelias");
		address.setChanged(LocalDate.now());
		address.setCreated(LocalDate.now());
	
		addressDTO.setCity("Bauru");
		addressDTO.setNeighborhood("Pedro Santos Drumond");
		addressDTO.setNumber(56);
		addressDTO.setStreet("Rua das Amelias");
		
	}

	@Test
	public void create() {
		when(mapper.toModel(any())).thenReturn(address);
		when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
		ResponseEntity<Address> address = services.create(addressDTO, UUID.randomUUID());
		assertNotNull(address);
	}

}
