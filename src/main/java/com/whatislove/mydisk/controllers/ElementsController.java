package com.whatislove.mydisk.controllers;


import com.whatislove.mydisk.dto.ElementListDTO;
import com.whatislove.mydisk.models.Element;
import com.whatislove.mydisk.util.ErrorResponse;
import com.whatislove.mydisk.util.ValidationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.Valid;
import java.util.Date;

@RestController
public class ElementsController {


    @PostMapping("/imports")
    public ResponseEntity<HttpStatus> imports(@RequestBody @Valid ElementListDTO elements,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationException("Sosi");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(ValidationException e){
        ErrorResponse response = new ErrorResponse(400, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException e){
        ErrorResponse response = new ErrorResponse(400, "Validation Failed");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
