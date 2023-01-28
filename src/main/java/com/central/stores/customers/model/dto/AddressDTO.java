package com.central.stores.customers.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "{street.not.empty}")
	private String street;
	@NotNull(message = "{number.not.null}")
	@Min(value=1, message = "{number.not.less.than}")
	private Integer number;
	@NotEmpty(message = "{neighborhood.not.empty}")
	private String neighborhood;
	@NotEmpty(message = "{city.not.empty}")
	private String city;

}
