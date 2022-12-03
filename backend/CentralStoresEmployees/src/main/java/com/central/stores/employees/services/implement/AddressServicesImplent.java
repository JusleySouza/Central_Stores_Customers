package com.central.stores.employees.services.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.central.stores.employees.config.LoggerConfig;
import com.central.stores.employees.model.Address;
import com.central.stores.employees.model.Employee;
import com.central.stores.employees.model.dto.AddressDTO;
import com.central.stores.employees.repository.AddressRepository;
import com.central.stores.employees.repository.EmployeesRepository;
import com.central.stores.employees.services.AddressServices;

@Component
public class AddressServicesImplent implements AddressServices {

	@Autowired
	EmployeesRepository employeesRepository;

	@Autowired
	AddressRepository addressRepository;
	
	Address address;
	
	Employee employee;
	
	@Override
	public ResponseEntity<List<AddressDTO>> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<AddressDTO> findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<AddressDTO> create(AddressDTO requestAddressDTO, UUID employeeId) {
		employee = new Employee();
		address = new Address();

		address.transformRequestAddressDTOToModel(requestAddressDTO);
		addressRepository.save(address);
		
		employee = employeesRepository.findById(employeeId).get();
		employee.setAddress(address);
		
		employeesRepository.save(employee);
		
		LoggerConfig.LOGGER_ADDRESS.info("Endereço do funcionário " + employee.getName() + " salvo com sucesso!!!");

		return new ResponseEntity<AddressDTO>(HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<AddressDTO> update(AddressDTO requestAddressDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<AddressDTO> delete(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
