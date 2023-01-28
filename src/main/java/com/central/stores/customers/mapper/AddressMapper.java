package com.central.stores.customers.mapper;

import java.time.LocalDate;

import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.dto.AddressDTO;

public final class AddressMapper {
	
	public static Address toModel(AddressDTO addressDTO) {
		return Address.builder()
				.street(addressDTO.getStreet())
				.number(addressDTO.getNumber())
				.neighborhood(addressDTO.getNeighborhood())
				.city(addressDTO.getCity())
				.created(LocalDate.now())
				.build();
	}
	
	public static AddressDTO toDTO(Address address) {
		return AddressDTO.builder()
				.street(address.getStreet())
				.number(address.getNumber())
				.neighborhood(address.getNeighborhood())
				.city(address.getCity())
				.build();
	}
	
	public static Address updateAddress(Address address, AddressDTO RequestAddressDTO) {
		address.setStreet(RequestAddressDTO.getStreet());
		address.setNumber(RequestAddressDTO.getNumber());
		address.setNeighborhood(RequestAddressDTO.getNeighborhood());
		address.setCity(RequestAddressDTO.getCity());
		address.setChanged(LocalDate.now());
		return address;
	}

}
