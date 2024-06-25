package com.annodocs.annodocsbackend.annotation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnotationRepository extends JpaRepository<AnnotationEntity, Long> {
    List<AnnotationEntity> findByUserId(Long userId);
}
