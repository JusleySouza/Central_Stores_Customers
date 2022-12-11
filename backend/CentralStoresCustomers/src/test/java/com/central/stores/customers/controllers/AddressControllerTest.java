package com.central.stores.customers.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.central.stores.customers.controller.AddressController;
import com.central.stores.customers.model.Address;
import com.central.stores.customers.services.implement.AddressServicesImplement;
import com.central.stores.customers.test.utils.ClassBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(controllers = AddressController.class)
class AddressControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AddressServicesImplement service;

	private Address address;

	private ObjectMapper objectMapper;

	private final String CONTEXT_PATH = "/customers";
	private final String CUSTOMER_ID = "/a54beaf5-fdb1-4cd9-85ce-36fe8b8b88fd";
	private final String PATH = "/address";
	private final String ADDRESS_ID = "/cf6239ee-e032-442a-a8b8-48f3cfb881e5";

	@BeforeEach
	void setUp() {
		address = ClassBuilder.addressBuilder();
		objectMapper = new ObjectMapper();
		
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	void create() throws Exception {
		mockMvc.perform(post(CONTEXT_PATH + CUSTOMER_ID + PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(address))).andExpect(status().isOk());

	}
	
	@Test
	void update() throws JsonProcessingException, Exception {
		mockMvc.perform(put(CONTEXT_PATH + CUSTOMER_ID + PATH + ADDRESS_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(address))).andExpect(status().isOk());
	}
	

}
