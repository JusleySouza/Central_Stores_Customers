package com.central.stores.customers.crypto;

import java.util.Base64;
import com.central.stores.customers.model.Customer;

import lombok.Generated;

@Generated
public final class Cryptography {

	public static Customer encode(Customer customer) {
		customer.setCpf(new String(Base64.getEncoder().encode(customer.getCpf().getBytes())));
		customer.setRg(new String(Base64.getEncoder().encode(customer.getRg().getBytes())));
		return customer;
	}
	
	public static Customer decode(Customer customer) {
		customer.setCpf(new String(Base64.getDecoder().decode(customer.getCpf().getBytes())));
		customer.setRg(new String(Base64.getDecoder().decode(customer.getRg().getBytes())));
		return customer;
	}

	public static String encodeCpf(String customerCpf) {
		customerCpf = (new String(Base64.getEncoder().encode(customerCpf.getBytes())));
		return customerCpf;
	}
	
}
