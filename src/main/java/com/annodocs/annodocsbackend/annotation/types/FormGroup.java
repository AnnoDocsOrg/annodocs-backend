package com.annodocs.annodocsbackend.annotation.types;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
public class FormGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formAnnotation_id", nullable = false)
    private FormEntity form;

    @OneToMany(mappedBy = "formGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FormField> formFields;

    @Setter
    @Getter
    private String groupName;

}