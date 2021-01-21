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

	@Column(name="start_date",nullable = false)
	private LocalDate startDate;

	@Column(name="end_date")
	private LocalDate endDate;
	
	
	@Column(name="adv_name")
	private String advName;
	
	@Column(name="image_status")
	private String imageStatus;

	@JsonIgnore
	@Lob
	@Column(name="adv_image")
	private byte[] advImage;
	
	@Column(name="image_url")
	private String link;
	
	@Column(name="is_posted")
	private Integer isPosted;
	
	
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "cust_id",nullable = false)
	private CustomerMaster customer;

	@ToString.Exclude
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "staff_id")
	private Staff staff;



}






