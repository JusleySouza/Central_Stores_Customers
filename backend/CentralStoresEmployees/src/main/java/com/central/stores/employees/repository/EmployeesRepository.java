package com.central.stores.employees.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.stores.employees.model.Employee;

public interface EmployeesRepository extends JpaRepository<Employee, UUID> {

}