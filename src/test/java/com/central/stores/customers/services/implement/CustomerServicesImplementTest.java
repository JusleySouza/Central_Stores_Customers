package com.central.stores.customers.services.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.central.stores.customers.crypto.Cryptography;
import com.central.stores.customers.exception.DuplicateDocumentsException;
import com.central.stores.customers.exception.ResourceNotFoundException;
import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.ListCustomer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;
import com.central.stores.customers.pagination.Pagination;
import com.central.stores.customers.repository.CustomersRepository;
import com.central.stores.customers.test.utils.ClassBuilder;


class CustomerServicesImplementTest {

	@InjectMocks
	private CustomersServicesImplement services;
	
	@Mock
	private Validator mockValidator;
	
	@Mock
	private CustomersRepository repository;
	
	private Customer customer;
	private RequestCustomerDTO requestCustomerDTO;
	private ResponseCustomerDTO responseCustomerDTO;
	private LocalValidatorFactoryBean validator;
	private Set<ConstraintViolation<Object>> violations;
	
	
	private Pageable pageable;
	private Page<Customer> pageCustomer;
	private Page<Customer> pageCustomerEmpty;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		UUID id = UUID.randomUUID();
		customer = ClassBuilder.customerBuilder();
		requestCustomerDTO = ClassBuilder.requestCustomerDTOBuilder();
		responseCustomerDTO = ClassBuilder.responseCustomerDTOBuilder();
		
		customer.setId(id);
		responseCustomerDTO.setId(id);
		customer.setAddress(ClassBuilder.addressBuilder());
		
		validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		
		pageable = Pagination.createPagination(1, 1, "name, DESC");
		pageCustomerEmpty = new PageImpl<Customer>(List.of(), pageable, 1L);
		pageCustomer = new PageImpl<Customer>(List.of(customer), pageable, 1L);
	}
	
	@Test
	public void findByCpf() throws Exception {
		customer = Cryptography.encode(customer);
		when(repository.findByCpf(anyString())).thenReturn(customer);
		ResponseCustomerDTO cust = services.findByCpf("12365478965");
		assertEquals(cust.toString(), responseCustomerDTO.toString());
	}
	
	@Test
	public void findByCpfResourceNotFoundException(){
		String messageError = "No records found for this cpf!!";
		customer = Cryptography.encode(customer);
		
		when(repository.findByCpf(anyString())).thenReturn(null);
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findByCpf("12365478965");
		}).getMessage();
		
		assertEquals(messageError, message);
	}

	@Test
	public void findByNeighborhood() {
		customer = Cryptography.encode(customer);
		when(repository.findAllByActiveTrueAndAddressNeighborhood(anyString(), any())).thenReturn(pageCustomer);
		ListCustomer listCustomers = services.findByNeighborhood("teste", 1, 1, "name, DESC");
		assertNotNull(listCustomers);
	}
	
	@Test
	public void findByNeighborhoodResourceNotFoundException() {
		String messageError = "No records found for this neighborhood!!";
		customer = Cryptography.encode(customer);
		
		when(repository.findAllByActiveTrueAndAddressNeighborhood(anyString(), any())).thenReturn(pageCustomerEmpty);
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findByNeighborhood("teste", 1, 1, "name, DESC");
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void findAll() {
		customer = Cryptography.encode(customer);
		when(repository.findAllByActiveTrue(any())).thenReturn(pageCustomer);
		ListCustomer customers = services.findAll(1, 1, "name, DESC");
		assertNotNull(customers);
		
	}
	
	@Test
	public void delete() {
		customer = Cryptography.encode(customer);
		customer.setActive(false);
		when(repository.findById(any())).thenReturn(Optional.of(customer));
		Customer customers = (Customer) services.delete(UUID.randomUUID());
		assertTrue(customers.getActive().equals(false));
	}
	
	@Test
	public void deleteResourceNotFoundException() {
		String messageError = "No records found for this id!!";
		customer = Cryptography.encode(customer);
		
		when(repository.findById(any())).thenReturn(Optional.ofNullable(null));
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.delete(UUID.randomUUID());
		}).getMessage();
				
		assertEquals(messageError, message);
	}
	
	@Test
	public void create() {
		ResponseEntity<Object>  customer = services.create(requestCustomerDTO);
		assertTrue(customer.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	@Test
	public void createWithMissingFields() {
		requestCustomerDTO.setCpf(null);
		
		violations = validator.validate(requestCustomerDTO);
		
		when(mockValidator.validate(any())).thenReturn(violations);
		
		ResponseEntity<Object> cust = services.create(requestCustomerDTO);
		
		assertTrue(cust.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		
	}
	
	@Test
	public void createDuplicateDocumentsException() {
		String messageError = "Documents already registered";
		when(repository.findByRg(anyString())).thenReturn(customer);
		when(repository.findByCpf(anyString())).thenReturn(customer);
		
		String message = assertThrows(DuplicateDocumentsException.class, () -> {
			services.create(requestCustomerDTO);
		}).getMessage();
				
		assertEquals(messageError, message);
		}
	
	@Test
	public void createWithDuplicateCpf() {
		String messageError = "Cpf already registered";
		when(repository.findByCpf(anyString())).thenReturn(customer);
		
		String message = assertThrows(DuplicateDocumentsException.class, () -> {
			services.create(requestCustomerDTO);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void createWithDuplicateRg() {
		String messageError = "Rg already registered";
		when(repository.findByRg(anyString())).thenReturn(customer);
		
		String message = assertThrows(DuplicateDocumentsException.class, () -> {
			services.create(requestCustomerDTO);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void update() {
		when(repository.findById(any())).thenReturn(Optional.of(customer));
		
		ResponseEntity<Object> customer = services.update(requestCustomerDTO, UUID.randomUUID());
		assertTrue(customer.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void updateResourceNotFoundException() {
		String messageError = "No records found for this id!!";
		
		when(repository.findById(any())).thenReturn(Optional.ofNullable(null));
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.update(requestCustomerDTO, UUID.randomUUID());
		}).getMessage();
		
		assertEquals(messageError, message);
		
	}
	
	@Test
	public void updateWithMissingFields() {
		
		requestCustomerDTO.setCpf(null);
		
		violations = validator.validate(requestCustomerDTO);
		
		when(mockValidator.validate(any())).thenReturn(violations);
		
		ResponseEntity<Object> customer = services.update(requestCustomerDTO, UUID.randomUUID());
		
		assertTrue(customer.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
}
