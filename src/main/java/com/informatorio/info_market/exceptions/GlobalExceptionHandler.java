package com.informatorio.info_market.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.informatorio.info_market.dto.error.ErrorResponseDto;
import com.informatorio.info_market.exceptions.badrequest.StockInsuficienteException;
import com.informatorio.info_market.exceptions.notfound.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex, WebRequest request){

        List<Map<String, String>> errors = ex.getFieldErrors().stream().map(fieldError -> {
            Map<String, String> error = new HashMap<>();
            error.put(fieldError.getField(), fieldError.getDefaultMessage());
            return error;
        }).toList();

        return ResponseEntity.badRequest().body(errors); //bad request se manda cuando el cliente manda algo erroneo

    }
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException e, WebRequest webRequest){
        //tratar el error

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(

            e,
            webRequest.getDescription(false), //tiene informacion de toda la request que se hizo
            HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND); //ResponseEntity es la informacion que se va a transmitir en el cuerpo de la respuesta y el codigo de respuesta


    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<ErrorResponseDto> handleStockInsuficienteException(StockInsuficienteException e, WebRequest webRequest){
        //tratar el error

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(

            e,
            webRequest.getDescription(false), //tiene informacion de toda la request que se hizo
            HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST); //ResponseEntity es la informacion que se va a transmitir en el cuerpo de la respuesta y el codigo de respuesta


    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String,String>> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String errorMsg = String.format("El valor '%s' no es válido para el parámetro '%s.'", e.getValue(), e.getName());

        return ResponseEntity.badRequest().body(Map.of("error", errorMsg));
    }
}
