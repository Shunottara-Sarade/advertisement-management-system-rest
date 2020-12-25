package com.capgemini.advertisement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StaffLogin {
	@Id
	private String email;
	private String pass;
	private String role;

}
