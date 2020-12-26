package com.capgemini.advertisement.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author Shweta and Ashwini
 *
 */

//This annotation is used to create and declare the table name
@Entity
@Table(name="customer_master")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerMaster 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id")
	private Integer custId;

	@NotNull
	@NotBlank(message="Please enter your name")
	@Size(min=2, max=30,message="First name should have atleast 2 characters")
	@Column(name = "cust_first_name")
	private String custFirstName;

	@NotNull
	@NotBlank(message="Please enter your name")
	@Size(min=2, max=30,message="Last name should have atleast 2 characters")
	@Column(name = "cust_last_name")
	private String custLastName;

	@NotNull
	@NotBlank(message="Please enter your email")
	@Email(message = "Email should be valid")
	@Pattern(regexp="[A-Za-z]+[0-9]*@[a-zA-Z]+.[a-zA-A]+")
	@Column(name = "cust_email")
	private String custEmail;

	@NotNull
	@NotBlank(message="Please enter your phone number")
	@Pattern(regexp="([7-9][0-9]{9})")
	@Column(name = "cust_mobile")
	private String custMobile;

	@NotNull
	@NotBlank(message="Please enter password")
	@Column(name = "cust_password")
	private String custPassword;

	@ToString.Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private Set<AdvertisementDetails> advertisement;
}
