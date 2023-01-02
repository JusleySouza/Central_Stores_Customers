package com.central.stores.customers.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
@NoArgsConstructor
@AllArgsConstructor
public class RequestCustomerDTO {

	@NotEmpty(message = "{name.not.empty}")
	private String name;
	@NotEmpty(message = "{cpf.not.empty}")
	private String cpf;
	@NotEmpty(message = "{rg.not.empty}")
	private String rg;
	@NotEmpty(message = "{gender.not.empty}")
	private String gender;
	@NotEmpty(message = "{phone.not.empty}")
	private String phone;
	@Email(message = "{email.not.valid}")
	@NotEmpty(message = "{email.not.empty}")
	private String email;

}
