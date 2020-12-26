package com.capgemini.advertisement.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 
 * @author Sapna and Dakshata
 *
 */
//This annotation is used to create and declare the table name
@Entity
@Table(name="advertisement_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="adv_type" ,nullable=false)
	private String advType;

	@Column(name="created_by")
	private String createdBy;


	@Column(name="adv_location")
	private String advLocation;

	@Column(name="start_date")
	private LocalDate startDate;

	@Column(name="end_date")
	private LocalDate endDate;

	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	@Column(name="adv_image")
	private byte[] advImage;

	@ToString.Exclude
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cust_id")
	private CustomerMaster customer;

	@ToString.Exclude
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "staff_id")
	private Staff staff;



}