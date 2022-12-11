package com.central.stores.customers.services.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.central.stores.customers.crypto.Cryptography;
import com.central.stores.customers.mapper.CustomerMapper;
import com.central.stores.customers.mapper.UpdateModel;
import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;
import com.central.stores.customers.repository.CustomersRepository;


class CustomerServicesImplementTest {

	@InjectMocks
	private CustomersServicesImplement services;
	
	@Mock
	private CustomerMapper mapper;
	
	@Mock
	private CustomersRepository repository;
	
	private Customer customer;
	private RequestCustomerDTO requestCustomerDTO;
	private ResponseCustomerDTO responseCustomerDTO;
	private ResponseEntity<Customer> responseEntity;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		customer = new Customer();
		requestCustomerDTO = new RequestCustomerDTO();
		responseCustomerDTO = new ResponseCustomerDTO();
		
		customer.setActive(true);
		customer.setChanged(LocalDate.now());
		customer.setCpf("12365478965");
		customer.setCreated(LocalDate.now());
		customer.setEmail("caio@castro.com");
		customer.setGender("masculino");
		customer.setName("Caio Castro");
		customer.setPhone("1111111111");
		customer.setRg("325698741");
		
		requestCustomerDTO.setCpf("12365478965");
		requestCustomerDTO.setEmail("caio@castro.com");
		requestCustomerDTO.setGender("masculino");
		requestCustomerDTO.setName("Caio Castro");
		requestCustomerDTO.setPhone("1111111111");
		requestCustomerDTO.setRg("325698741");
		
		responseCustomerDTO.setName("Caio Castro");
		responseCustomerDTO.setId(UUID.randomUUID());
		
	}
	
	@Test
	public void findByCpf() throws Exception {
		customer = Cryptography.encode(customer);
		when(repository.findByCpf(anyString())).thenReturn(customer);
		Customer cust = services.findByCpf("12365478965").getBody();
		assertEquals(cust, customer);
	}

	@Test
	public void findByNeighborhood() {
		customer = Cryptography.encode(customer);
		when(repository.findAllByActiveTrueAndAddressNeighborhood(anyString())).thenReturn(List.of(customer));
		ResponseEntity<List<Customer>> listCustomers = services.findByNeighborhood("teste");
		assertNotNull(listCustomers);
	}
	
	@Test
	public void findAll() {
		customer = Cryptography.encode(customer);
		when(repository.findAllByActiveTrue()).thenReturn(List.of(customer));
		ResponseEntity<List<Customer>> customers = services.findAll();
		assertNotNull(customers);
		
	}
	
	@Test
	public void delete() {
		customer = Cryptography.encode(customer);
		when(repository.findById(any())).thenReturn(Optional.of(customer));
		when(mapper.customerDelete(any())).thenReturn(customer);
		ResponseEntity<ResponseCustomerDTO> customers = services.delete(UUID.randomUUID());
		assertTrue(customers.getStatusCodeValue()== HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void create() {
		when(mapper.toModel(any())).thenReturn(customer);
		when(mapper.modelToResponseCustomerDTO(any())).thenReturn(responseCustomerDTO);
		ResponseEntity<ResponseCustomerDTO> customer = services.create(requestCustomerDTO);
		assertNotNull(customer);
	}
	
	@Test
	public void update() {
		when(repository.findById(any())).thenReturn(Optional.of(customer));
		when(mapper.modelToResponseCustomerDTO(any())).thenReturn(responseCustomerDTO);
		
		ResponseEntity<ResponseCustomerDTO> customer = services.update(requestCustomerDTO, UUID.randomUUID());
		assertNotNull(customer);
	}
	
}
