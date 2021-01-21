package com.capgemini.advertisement.controller;

import java.net.URLConnection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.advertisement.entity.AddAdvertisement;
import com.capgemini.advertisement.entity.AdvertisementDetails;
import com.capgemini.advertisement.exception.AdvertisementException;
import com.capgemini.advertisement.service.AdvertisementService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

 

/**
 * 
 * @author Sapna and Dakshata
 *
 */
@RestController
@RequestMapping("/api/advertisement")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class AdvertisementController 
{
    @Autowired
    private AdvertisementService advertisementService;

 

    /**
     * @param id
     * Get Advertisement by Id
     * @return 
     */
    @ApiOperation(value = "Get Advertisement by Id",
            response = AdvertisementDetails.class,tags="get-advertisement-by-id",
            consumes="id",httpMethod = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDetails> getAdvertisementById(@PathVariable Integer id){
        try 
        {
            AdvertisementDetails advertisements= advertisementService.getAdvertisementById(id);
            log.info("Advertisement added"+ advertisements);
            return new ResponseEntity<>(advertisements,HttpStatus.OK);
        }
        catch(AdvertisementException advertisementException) 
        {
            log.error(advertisementException.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,advertisementException.getMessage());
        }
    }
    /**
     * Get All Advertisement
     * @return
     */
    @ApiOperation(value = "Get All Advertisement",
            response = AdvertisementDetails.class,tags="get-all-advertisement",
            httpMethod = "GET")
    @GetMapping("/")
    public ResponseEntity<List<AdvertisementDetails>> getAllAdvertisements()
    {
        try
        {
            List<AdvertisementDetails> advertisementList = advertisementService.getAllAdvertisement();
            log.info("Returning all customer details");
            return new ResponseEntity<>(advertisementList,HttpStatus.OK);
        }
        catch(AdvertisementException advertisementException)
        {
            log.error(advertisementException.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,advertisementException.getMessage());
        }
    }

 

    /**
     * 
     * @param cid
     * @param sid
     * @param advertisements
     * Add Advertisement
     * @return
     */
    
    @ApiOperation(value = "Add Advertisement",
            consumes = "receives Advertisement object as request body",
            response =String.class)
    @PostMapping("/")
    public Integer addAdvertisements(@RequestBody AddAdvertisement advertisements) {
        try 
        {
            AdvertisementDetails advertisementDetails= advertisementService.addAdvertisement(advertisements);

            if(advertisementDetails.getId() !=0)
            {
                log.info("advertisement:"+advertisementDetails.getId()+" added to database");
                return advertisementDetails.getId();
            }
            else 
            {
                log.debug("Unable to add advertisement");
                return 0;
            }

 

        }
        catch(AdvertisementException advertisementException)
        {
            log.error(advertisementException.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,advertisementException.getMessage());
        }
    }

 

    /**
     * @param id
     * Delete Advertisement
     * @return
     */
    @ApiOperation(value = "Delete Advertisement",
            consumes = "id",
            response =String.class)
    @DeleteMapping("/{id}")
    public String deleteAdvertisements(@PathVariable Integer id) 
    {
        try 
        {
            Integer status= advertisementService.deleteAdvertisement(id);
            if(status ==1) 
            {
                log.info("advertisement: "+id+" deleted from database");
                return "advertisement: "+id+" deleted from database";
            }
            else 
            {
                log.debug("Unable to delete advertisement from database");
                return "Unable to delete advertisement from database";
            }

 

        }
        catch(AdvertisementException advertisementException)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,advertisementException.getMessage());
        }
    }

 

    /**
     * @param advertisements
     * Update Advertisement
     * @return
     */
    @ApiOperation(value = "Update Advertisement",
    		consumes = "receives Advertisement object as request body",
    		response =AdvertisementDetails.class)
    		@PutMapping("/")
    		public ResponseEntity<AdvertisementDetails> updateAdvertisements(@RequestBody AddAdvertisement advertisements) {
    		try
    		{
    		AdvertisementDetails updatedAdvertisement= advertisementService.updateAdvertisement(advertisements);
    		log.info("Advertisement: "+ advertisements.getId()+ " updated");
    		return new ResponseEntity<>(updatedAdvertisement,HttpStatus.OK);
    		}
    		catch(AdvertisementException advertisementException)
    		{
    		log.error(advertisementException.getMessage());
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,advertisementException.getMessage());
    		}
    		}
    
    @PutMapping("/upload/{id}" )
    public String uploadToDB(@RequestPart("file") MultipartFile file, @PathVariable Integer id) {
    	try {
    		boolean isUploaded=advertisementService.uploadAdvertisementImage(file, id);
    		
    		if (isUploaded) {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/advertisement/download/" + id).toUriString();
			
				return fileDownloadUri;
			} else {
				return "Could not upload certificate!";
			}
    	}catch (AdvertisementException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
    }
    
    @GetMapping("/download/{id}")
    public ResponseEntity downloadFromDB(@PathVariable Integer id) {
    	try {
    		String advertisementName=advertisementService.getAdvertisementNameById(id);
    		String type = URLConnection.guessContentTypeFromName(advertisementName);
    		byte[] advertisements=advertisementService.getAdveertisementsById(id);
    		return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(type))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" 
							+ advertisementName + "\"")
					.body(advertisements);
    		
    	}catch (AdvertisementException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
    }
    
    @ApiOperation(value = "Get Advertisement Id by email",
            response = Integer.class,tags="get-id-by-email",
            consumes="email",httpMethod = "GET")
    @GetMapping("/email/{email}")
    public Integer getAdvertisementById(@PathVariable String email){
        try
        {
            Integer Id= advertisementService.getIdByEmail(email);
            if(Id != null)
            {
                log.info("Advertisement added"+ Id);
                return Id;
            }
            else
            {
                return 0;
            }
           
        }
        catch(AdvertisementException advertisementException)
        {
            log.error(advertisementException.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,advertisementException.getMessage());
        }
    }
    @ApiOperation(value = "Get Advertisement by customer",
            response = AdvertisementDetails.class,tags="get-all-advertisement",
            httpMethod = "GET")
    @GetMapping("/customer/{email}")
    public ResponseEntity<List<AdvertisementDetails>> getAdvertisementsByCustomer(@PathVariable String email)
    {
        try
        {
            List<AdvertisementDetails> advertisementList = advertisementService.findByCustomer(email);
            log.info("Returning all customer details");
            return new ResponseEntity<>(advertisementList,HttpStatus.OK);
        }
        catch(AdvertisementException advertisementException)
        {
            log.error(advertisementException.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,advertisementException.getMessage());
        }
    }
    
    
    @ApiOperation(value = "Get Images by type",
            tags="get-images-by-type",
            consumes="advType",httpMethod = "GET")
    @GetMapping("/image/{advType}")
    public List<AdvertisementDetails> getImagesByType(@PathVariable String advType){
        try
        {
            List<AdvertisementDetails> images= advertisementService.getImagesByType(advType);
            if(images.size()!=0)
            {
                log.info("images found");
                return images;
            }
            else
            {
                return null;
            }
           
        }
        catch(AdvertisementException advertisementException)
        {
            log.error(advertisementException.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,advertisementException.getMessage());
        }
    }
}