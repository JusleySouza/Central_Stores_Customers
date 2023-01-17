package com.central.stores.customers.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.stores.customers.model.Customer;

public interface CustomersRepository extends JpaRepository<Customer, UUID> {
	List<Customer> findAllByActiveTrue();
	Customer findByCpf(String cpf);
	Customer findByRg(String rg);
	List<Customer> findAllByActiveTrueAndAddressNeighborhood(String neighborhood);
}
