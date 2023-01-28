package com.central.stores.customers.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.central.stores.customers.model.Customer;

public interface CustomersRepository extends JpaRepository<Customer, UUID> {
	Page<Customer> findAllByActiveTrue(Pageable pageable);
	Customer findByCpf(String cpf);
	Customer findByRg(String rg);
	Page<Customer> findAllByActiveTrueAndAddressNeighborhood(String neighborhood, Pageable pageable);
}
