package com.central.stores.employees.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.central.stores.employees.constants.Conf;
import com.central.stores.employees.model.dto.AddressDTO;

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
	@Column(updatable = false, nullable = false)
	private UUID id;
	@Column(nullable = false)
	private String street;
	@Column(nullable = false)
	private Integer number;
	@Column(nullable = false)
	private String neighborhood;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private Boolean employeeIsActive;
	@DateTimeFormat(pattern = Conf.dateFormat)
	private Date created;
	@DateTimeFormat(pattern = Conf.dateFormat)
	private Date changed;
	
	public void transformRequestAddressDTOToModel(AddressDTO requestAddressDTO) {
		this.created = new Date();
		this.employeeIsActive = Boolean.TRUE;
		this.city = requestAddressDTO.getCity();
		this.street = requestAddressDTO.getStreet();
		this.number = requestAddressDTO.getNumber();
		this.neighborhood = requestAddressDTO.getNeighborhood();
	}
}
