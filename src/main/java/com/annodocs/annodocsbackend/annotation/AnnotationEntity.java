package com.annodocs.annodocsbackend.annotation;

import com.annodocs.annodocsbackend.gesetz.paragraph.TextAbschnitt;
import com.annodocs.annodocsbackend.gesetz.paragraph.paragraphEntity;
import com.annodocs.annodocsbackend.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "annotation_type", discriminatorType = DiscriminatorType.STRING)
public abstract class AnnotationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long aID;

    @Setter
    @Getter
    private String ersteller;

    @OneToMany(mappedBy = "annotation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Setter
    @Getter
    private List<AnnotatedPart> annotationAbschnitte;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    @Temporal(TemporalType.DATE)
    private Date datum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Setter
    @Getter
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paragraph_id", nullable = false)
    @Setter
    @Getter
    private paragraphEntity paragraph;

}
