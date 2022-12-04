package com.central.stores.employees.services.implement;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.central.stores.employees.config.LoggerConfig;
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
		List<Employee> listEmployees = repository.findAllByActiveTrue();
		
		LoggerConfig.LOGGER_EMPLOYEE.info("Listagem de clientes realizada com sucesso!!!");
		
		return new ResponseEntity<List<Employee>>(listEmployees, HttpStatus.OK);
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
		
		LoggerConfig.LOGGER_EMPLOYEE.info("Funcionário " + employee.getName() + " salvo com sucesso!!!");
		
		return new ResponseEntity<ResponseEmployeeDTO>(responseEmployeeDTO, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<ResponseEmployeeDTO> update(RequestEmployeeDTO requestEmployeeDTO, UUID employeeId) {
		responseEmployeeDTO = new ResponseEmployeeDTO();
		
		employee = repository.findById(employeeId).get();
		employee = updateModel(employee, requestEmployeeDTO);
		
		repository.save(employee);
		
		responseEmployeeDTO.transformModelToResponseEmployeeDTO(employee);
		
		
		LoggerConfig.LOGGER_EMPLOYEE.info("Dados do funcionário " + employee.getName() + " atualizados com sucesso!!!");
		
		return new ResponseEntity<ResponseEmployeeDTO>(responseEmployeeDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseEmployeeDTO> delete(UUID employeeId) {
		employee = repository.findById(employeeId).get();
		employee.setActive(Boolean.FALSE);
		employee.getAddress().setEmployeeIsActive(Boolean.FALSE);
		repository.save(employee);
		
		LoggerConfig.LOGGER_EMPLOYEE.info("Funcionário " + employee.getName() + " deletado com sucesso!!!");
		
		return new ResponseEntity<ResponseEmployeeDTO>(HttpStatus.NO_CONTENT);
	}
	
	private Employee updateModel(Employee employee, RequestEmployeeDTO requestEmployeeDTO) {
		employee.setChanged(new Date());
		employee.setRg(requestEmployeeDTO.getRg());
		employee.setCpf(requestEmployeeDTO.getCpf());
		employee.setName(requestEmployeeDTO.getName());
		employee.setRole(requestEmployeeDTO.getRole());
		employee.setPhone(requestEmployeeDTO.getPhone());
		employee.setEmail(requestEmployeeDTO.getEmail());
		employee.setGender(requestEmployeeDTO.getGender());
		
		return employee;
		
	}
}