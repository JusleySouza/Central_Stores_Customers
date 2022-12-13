package com.central.stores.customers.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.central.stores.customers.controller.CustomerController;
import com.central.stores.customers.model.Customer;
import com.central.stores.customers.services.implement.CustomersServicesImplement;
import com.central.stores.customers.test.utils.ClassBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(controllers = CustomerController.class)
class CustomersControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomersServicesImplement services;
	private ObjectMapper objectMapper;
	private Customer customer;
	
	private final String CONTEXT_PATH = "/customers";
	private final String PATH_FIND_ALL = "/list";
	private final String CUSTOMER_CPF = "12345678987";
	private final String QUERY_PARAM_KEY = "neighborhood";
	private final String QUERY_PARAM_VALUE = "testes";
	private final String CUSTOMER_ID = "/a54beaf5-fdb1-4cd9-85ce-36fe8b8b88fd";
	
	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
		customer = ClassBuilder.customerBuider();
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	void listCustomers() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_ALL))
		.andExpect(status().isOk());
	}
	
	@Test
	void findByCpf() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_ALL + CUSTOMER_CPF))
		.andExpect(status().isOk());
	}
	
	@Test
	void findByNeighborhood() throws Exception {
		mockMvc.perform(get(CONTEXT_PATH).param(QUERY_PARAM_KEY, QUERY_PARAM_VALUE))
		.andExpect(status().isOk());
	}

	@Test
	void create() throws Exception {
		mockMvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer))).andExpect(status().isCreated());

	}
	
	@Test
	void update() throws Exception {
		mockMvc.perform(put(CONTEXT_PATH + CUSTOMER_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer))).andExpect(status().isOk());

	}
	
	@Test
	void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(CONTEXT_PATH + CUSTOMER_ID)).andExpect(status().isNoContent());
	}
	
}
