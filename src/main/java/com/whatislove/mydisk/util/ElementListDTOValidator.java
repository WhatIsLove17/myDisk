package com.whatislove.mydisk.util;

import com.whatislove.mydisk.dto.ElementDTO;
import com.whatislove.mydisk.dto.ElementListDTO;
import com.whatislove.mydisk.models.Element;
import com.whatislove.mydisk.models.Type;
import com.whatislove.mydisk.services.ElementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class ElementListDTOValidator implements Validator {
    private final ElementsService elementsService;

    @Autowired
    public ElementListDTOValidator(ElementsService elementsService) {
        this.elementsService = elementsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ElementListDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ElementListDTO elementListDTO = (ElementListDTO) o;

        var elements = elementListDTO.getItems();

        HashSet elementSet = new HashSet(elements.stream().map(el -> el.getId())
                .collect(Collectors.toList()));


        if (elementSet.size() < elements.size())
            errors.rejectValue("items", "duplicate ids");

        for(ElementDTO element : elements){
            if (element.getId() == null)
                errors.rejectValue("items", "Id is null");

            Element parent = elementsService.findById(element.getParentId()).orElse(null);

            if (parent != null){
                if (parent.getType().equals(Type.FILE))
                    errors.rejectValue("items", "Parent should be folder");
            }

            if (element.getType().equals(Type.FOLDER)){
                if (element.getUrl() != null)
                    errors.rejectValue("items", "Folder url should be null");

                if (element.getSize() != 0)
                    errors.rejectValue("items", "Folder size should be equals zero");
            }
            else{
                if (element.getUrl() != null){
                    if (element.getUrl().length() > 255)
                        errors.rejectValue("items", "Url size should be less than 255");
                }

                if (element.getSize() == 0)
                    errors.rejectValue("items", "File size should be more than zero");
            }

        }

    }
}
