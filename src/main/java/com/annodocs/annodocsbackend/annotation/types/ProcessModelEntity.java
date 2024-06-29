package com.annodocs.annodocsbackend.annotation.types;

import com.annodocs.annodocsbackend.annotation.AnnotationEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("PROCESS_MODEL")
public class ProcessModelEntity extends AnnotationEntity {

    @Setter
    @Getter
    private String bpmnXml;
}
