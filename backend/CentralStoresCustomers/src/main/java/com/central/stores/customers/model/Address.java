package com.central.stores.customers.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.central.stores.customers.constants.Conf;
import com.central.stores.customers.model.dto.AddressDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false )
	private UUID id;
	@Column(nullable = false )
	private String street;
	@Column(nullable = false )
	private Integer number;
	@Column(nullable = false )
	private String neighborhood;
	@Column(nullable = false )
	private String city;
	@Column(nullable = false )
	@DateTimeFormat(pattern = Conf.dateFormat)
	private Date created;
	@DateTimeFormat(pattern = Conf.dateFormat)
	private Date changed;
	
	public void transformRequestAddressDTOToModel(AddressDTO requestAddressDTO) {
		this.street = requestAddressDTO.getStreet();
		this.number = requestAddressDTO.getNumber();
		this.neighborhood = requestAddressDTO.getNeighborhood();
		this.city = requestAddressDTO.getCity();
		this.created = new Date();
	}
}
