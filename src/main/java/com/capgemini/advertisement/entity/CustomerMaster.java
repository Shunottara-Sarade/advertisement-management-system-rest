package com.capgemini.advertisement.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="customer_master")
public class CustomerMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
	private Integer custId;
   @Column(name = "cust_first_name")
	private String custFirstName;
    @Column(name = "cust_last_name")
	private String custLastName;
    @Column(name = "cust_email")
	private String custEmail;
    @Column(name = "cust_mobile")
	private String custMobile;
   @Column(name = "cust_password")
	private String custPassword;
	
   @ToString.Exclude
   @JsonIgnore
	@OneToMany(mappedBy = "customer")
	private Set<AdvertisementDetails> advertisement;


}
