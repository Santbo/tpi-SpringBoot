package com.informatorio.info_market.exceptions.notfound;

public class NotFoundException extends RuntimeException {
    
    public NotFoundException(){

    }

    public NotFoundException(String msg){
        super(msg);
    }

    public NotFoundException(String msg, Throwable cause){

        super(msg, cause);
    }

    public NotFoundException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace){

        super(msg, cause, enableSuppression, writableStackTrace);
    
    }

    public NotFoundException(Throwable cause){

        super(cause);
    
    }





}
