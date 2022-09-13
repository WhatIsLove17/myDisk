package com.whatislove.mydisk.services;

import com.whatislove.mydisk.models.Element;
import com.whatislove.mydisk.repositories.ElementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ElementsService {

    private final ElementsRepository elementsRepository;

    @Autowired
    public ElementsService(ElementsRepository elementsRepository) {
        this.elementsRepository = elementsRepository;
    }

    @Transactional
    public void imports(List<Element> elementList, Date date) {
        List resultList = elementList.stream().map(el -> enrichElement(el, date)).collect(Collectors.toList());

        elementsRepository.saveAll(resultList);
    }


    public Optional<Element> findById(String id){
        return elementsRepository.findById(id);
    }

    private Element enrichElement(Element element, Date date) {
        element.setDate(date);
        return element;
    }
}
