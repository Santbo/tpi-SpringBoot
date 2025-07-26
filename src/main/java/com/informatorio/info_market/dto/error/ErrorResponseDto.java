package com.informatorio.info_market.dto.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String apiPath; //url del recurso que se quiso ingresar
    private HttpStatus status;
    private String errorMessage;
    private LocalDateTime errorTime;

    public ErrorResponseDto(Exception e, String description, HttpStatus status){

        this.setApiPath( description );
        this.setStatus( status );
        this.setErrorMessage( e.getMessage() );
        this.setErrorTime( LocalDateTime.now() );


    }
}
