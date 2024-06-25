package com.annodocs.annodocsbackend.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnotationService {

    @Autowired
    private AnnotationRepository annotationRepository;

    public void saveAnnotation(AnnotationEntity annotation) {
        annotationRepository.save(annotation);
    }

    public List<AnnotationEntity> getAnnotationsByUserId(Long userId) {
        return annotationRepository.findByUserId(userId);
    }
}
