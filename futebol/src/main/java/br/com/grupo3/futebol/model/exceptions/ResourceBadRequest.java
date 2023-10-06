package br.com.grupo3.futebol.model.exceptions;

public class ResourceBadRequest extends RuntimeException{
    
    public static final long serialVersion = 1L;

    public ResourceBadRequest(String message){
        super(message);
    }

}