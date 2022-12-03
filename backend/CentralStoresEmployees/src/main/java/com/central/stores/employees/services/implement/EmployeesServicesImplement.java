package com.central.stores.employees.services.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.central.stores.employees.model.Employee;
import com.central.stores.employees.model.dto.RequestEmployeeDTO;
import com.central.stores.employees.model.dto.ResponseEmployeeDTO;
import com.central.stores.employees.repository.EmployeesRepository;
import com.central.stores.employees.services.EmployeesServices;

@Component
public class EmployeesServicesImplement implements EmployeesServices {

	@Autowired
	EmployeesRepository repository;

	ResponseEmployeeDTO responseEmployeeDTO;
	
	Employee employee;

	@Override
	public ResponseEntity<List<Employee>> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Employee> findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseEmployeeDTO> create(RequestEmployeeDTO requestEmployeeDTO) {
		employee = new Employee();
		responseEmployeeDTO = new ResponseEmployeeDTO();
		
		employee.transformRequestEmployeeDTOToModel(requestEmployeeDTO);

		repository.save(employee);
		
		responseEmployeeDTO.transformModelToResponseEmployeeDTO(employee);

		return new ResponseEntity<ResponseEmployeeDTO>(responseEmployeeDTO, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<Employee> update(RequestEmployeeDTO employeeDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Employee> delete(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}
}