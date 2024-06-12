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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter
    @Getter
    private Long id;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FormField> formFields;

}
