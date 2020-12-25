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
    
    @Column(name = "staff_first_name" , nullable = false)
    private String firstName;
    
    @Column(name = "staff_last_name" , nullable = false)
    private String lastName;
    
    @Column(name = "staff_email" , nullable = false)
    private String email;
    
    @Column(name = "staff_mobile" )
    private String mobileNo;
    
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Column(name = "password" , nullable = false)
    private String password;
    
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    private Set<AdvertisementDetails> advertisement;
    
    
    
}
