package com.annodocs.annodocsbackend.annotation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class AnnotatedPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private int textID;

    @Setter
    @Getter
    private int start;

    @Setter
    @Getter
    private int end;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "annotation_id", nullable = false)
    private AnnotationEntity annotation;

}
