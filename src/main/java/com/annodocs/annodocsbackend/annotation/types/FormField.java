package com.annodocs.annodocsbackend.annotation.types;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class FormField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", nullable = false)
    private FormEntity form;

    @Setter
    @Getter
    private String fieldName;

    @Setter
    @Getter
    private String fieldType;

    @Setter
    @Getter
    private String fieldValue;
}
