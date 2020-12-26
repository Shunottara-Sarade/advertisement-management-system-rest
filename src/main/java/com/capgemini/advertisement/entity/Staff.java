package com.capgemini.advertisement.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
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
@Table(name = "staff")

public class Staff 
{
    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffId;
    
    @NotNull
	@NotBlank(message="Please enter your name")
	@Size(min=2, max=30,message="First name should have atleast 2 characters")
    @Column(name = "staff_first_name" , nullable = false)
    private String firstName;
    
    @NotNull
	@NotBlank(message="Please enter your name")
	@Size(min=2, max=30,message="Last name should have atleast 2 characters")
    @Column(name = "staff_last_name" , nullable = false)
    private String lastName;
    
    @NotNull
	@NotBlank(message="Please enter your email")
    @Email(message = "Email should be valid")
	@Pattern(regexp="[A-Za-z]+[0-9]*@[a-zA-Z]+.[a-zA-A]+")
    @Column(name = "staff_email" , nullable = false)
    private String email;
    
    @NotNull
	@NotBlank(message="Please enter your phone number")
	@Pattern(regexp="([7-9][0-9]{9})")
    @Column(name = "staff_mobile" )
    private String mobileNo;
    
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @NotNull
	@NotBlank(message="Please enter password")
    @Column(name = "password" , nullable = false)
    private String password;
    
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    private Set<AdvertisementDetails> advertisement;
    
    
    
}
