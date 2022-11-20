package com.central.stores.customers.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false )
	private UUID id;
	@Column(nullable = false )
	private String phone;
	@Column(nullable = false )
	private String email;
	@Column(nullable = false )
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date created;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date changed;
	
}
