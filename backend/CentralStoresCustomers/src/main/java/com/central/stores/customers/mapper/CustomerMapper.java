package com.central.stores.customers.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.RequestCustomerDTO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	@Mappings({
		@Mapping(target= "created", expression = "java( java.time.LocalDate.now())"),
		@Mapping(target= "active", expression = "java(java.lang.Boolean.TRUE)"),
		@Mapping(target= "id", ignore= true),
		@Mapping(target= "changed", ignore= true),
		@Mapping(target= "address", ignore= true),
	})
	Customer toModel(RequestCustomerDTO RequestCustomerDTO);
	
}
