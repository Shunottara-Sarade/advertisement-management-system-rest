package com.capgemini.advertisement.entity;
import java.time.LocalDate;

 

import lombok.Getter;
import lombok.Setter;

 

@Getter
@Setter
public class AddAdvertisement 
{
    private Integer staffId;
    private Integer custId;
    private Integer id;
    private String advType;
    private String createdBy;
    private String advLocation;
    private LocalDate startDate;
    private LocalDate endDate;
    private String advName;
    private String link;
    private Integer isPosted;
}