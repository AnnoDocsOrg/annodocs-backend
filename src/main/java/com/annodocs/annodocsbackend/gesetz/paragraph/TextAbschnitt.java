package com.annodocs.annodocsbackend.gesetz.paragraph;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class TextAbschnitt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long ID;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paragraph_id", nullable = false)
    private paragraphEntity paragraph;

    @Getter
    @Setter
    private String text;
}
