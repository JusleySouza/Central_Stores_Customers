package com.central.stores.customers.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.dto.AddressDTO;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	@Mappings({
		@Mapping(target= "created", expression = "java(java.time.LocalDate.now())"),
		@Mapping(target= "id", ignore= true),
		@Mapping(target= "changed", ignore= true),
	})
	Address toModel(AddressDTO addressDTO);

}
