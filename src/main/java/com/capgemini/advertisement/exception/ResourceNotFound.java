package com.capgemini.advertisement.exception;

 

public class ResourceNotFound extends RuntimeException {

 

     /**
     * 
     */
    private static final long serialVersionUID = 1L;

 

    public ResourceNotFound(String message){
            super(message);
        }
}