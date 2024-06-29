package com.annodocs.annodocsbackend.annotation.types;

import com.annodocs.annodocsbackend.annotation.AnnotationEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@DiscriminatorValue("FORM")
public class FormEntity extends AnnotationEntity {

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FormGroup> formGroups;

}
