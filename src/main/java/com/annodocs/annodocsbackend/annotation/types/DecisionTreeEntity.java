package com.annodocs.annodocsbackend.annotation.types;

import com.annodocs.annodocsbackend.annotation.AnnotationEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@DiscriminatorValue("DECISION_TREE")
public class DecisionTreeEntity extends AnnotationEntity {

    @Setter
    @Getter
    @OneToMany(mappedBy = "decisionTree", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TreeNode> TreeNodes;

}

