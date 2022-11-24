package com.central.stores.customers.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.stores.customers.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
