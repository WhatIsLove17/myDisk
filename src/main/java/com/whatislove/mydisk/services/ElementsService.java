package com.whatislove.mydisk.services;

import com.whatislove.mydisk.repositories.ElementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ElementsService {

    private final ElementsRepository elementsRepository;

    @Autowired
    public ElementsService(ElementsRepository elementsRepository) {
        this.elementsRepository = elementsRepository;
    }


}
