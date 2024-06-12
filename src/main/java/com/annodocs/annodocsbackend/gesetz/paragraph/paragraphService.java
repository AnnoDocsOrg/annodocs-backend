package com.annodocs.annodocsbackend.gesetz.paragraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class paragraphService {

    @Autowired
    private paragraphRepository paragraphRepository;

    public Optional<paragraphEntity> getParagraph(Long id) {
        return paragraphRepository.findById(id);
    }
}
