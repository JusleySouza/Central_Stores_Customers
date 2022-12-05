package com.central.stores.customers.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	@Mappings({
		@Mapping(target= "created", expression = "java(java.time.LocalDate.now())"),
		@Mapping(target= "active", expression = "java(java.lang.Boolean.TRUE)"),
		@Mapping(target= "id", ignore= true),
		@Mapping(target= "changed", ignore= true),
		@Mapping(target= "address", ignore= true),
	})
	Customer toModel(RequestCustomerDTO requestCustomerDTO);
	
	@Mappings({
		@Mapping(target= "changed", expression = "java(java.time.LocalDate.now())"),
		@Mapping(target= "created", ignore = true),
		@Mapping(target= "id", ignore= true),
		@Mapping(target= "address", ignore= true),
		@Mapping(target= "active", ignore = true),
	})
	Customer updateModel(RequestCustomerDTO requestCustomerDTO);
	
	@Mappings({
		@Mapping(target= "changed", expression = "java(java.time.LocalDate.now())"),
		@Mapping(target= "active", expression = "java(java.lang.Boolean.FALSE)"),
		@Mapping(target= "created", ignore = true),
		@Mapping(target= "id", ignore= true),
		@Mapping(target= "address", ignore= true),
		@Mapping(target= "name", ignore = true),
		@Mapping(target= "cpf", ignore= true),
		@Mapping(target= "rg", ignore= true),
		@Mapping(target= "gender", ignore = true),
		@Mapping(target= "phone", ignore= true),
		@Mapping(target= "email", ignore= true),
	})
	Customer customerDelete(Customer customer);
	
	ResponseCustomerDTO modelToResponseCustomerDTO(Customer customer);
	
}
