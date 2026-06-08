package com.vikas.linkedin.postsService.exception;

public class BadRequestException extends RuntimeException{


    public BadRequestException(String message){
        super(message);
    }

}
