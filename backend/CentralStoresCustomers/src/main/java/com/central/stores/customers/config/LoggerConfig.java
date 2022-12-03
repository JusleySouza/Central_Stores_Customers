package com.central.stores.customers.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.central.stores.customers.services.implement.AddressServicesImplement;
import com.central.stores.customers.services.implement.CustomersServicesImplement;

public class LoggerConfig {

	public static final Logger LOGGER_CUSTOMER = LoggerFactory.getLogger(CustomersServicesImplement.class);
	public static final Logger LOGGER_ADDRESS = LoggerFactory.getLogger(AddressServicesImplement.class);
	
}
