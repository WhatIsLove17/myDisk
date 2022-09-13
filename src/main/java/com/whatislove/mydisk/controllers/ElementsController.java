package com.whatislove.mydisk.controllers;


import com.whatislove.mydisk.dto.ElementDTO;
import com.whatislove.mydisk.dto.ElementListDTO;
import com.whatislove.mydisk.models.Element;
import com.whatislove.mydisk.services.ElementsService;
import com.whatislove.mydisk.util.ElementListDTOValidator;
import com.whatislove.mydisk.util.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ElementsController {

    private final ElementsService elementsService;
    private final ModelMapper modelMapper;

    private final ElementListDTOValidator elementListDTOValidator;


    @Autowired
    public ElementsController(ElementsService elementsService, ModelMapper modelMapper, ElementListDTOValidator elementListDTOValidator) {
        this.elementsService = elementsService;
        this.modelMapper = modelMapper;
        this.elementListDTOValidator = elementListDTOValidator;
    }

    @PostMapping("/imports")
    public ResponseEntity<HttpStatus> imports(@RequestBody @Valid ElementListDTO elements,
                                              BindingResult bindingResult){

        elementListDTOValidator.validate(elements, bindingResult);

        if (bindingResult.hasErrors()){
            throw new ValidationException("Validation Failed");
        }

        List<Element> resultList = elements.getItems()
                .stream()
                .map(this::convertToElement).collect(Collectors.toList());








        elementsService.imports(resultList, elements.getUpdateDate());

        return new ResponseEntity<>(HttpStatus.OK);
    }


    private Element convertToElement(ElementDTO elementDTO){
        return modelMapper.map(elementDTO, Element.class);
    }

    private ElementDTO convertToDTO(Element element){
        return modelMapper.map(element, ElementDTO.class);
    }

    /*@ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(ValidationException e){
        ErrorResponse response = new ErrorResponse(400, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException e){
        ErrorResponse response = new ErrorResponse(400, "Validation Failed");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(ConstraintViolationException e){
        ErrorResponse response = new ErrorResponse(400, "Validation Failed");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }*/

}
