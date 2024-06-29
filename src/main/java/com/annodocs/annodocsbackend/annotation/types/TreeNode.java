package com.annodocs.annodocsbackend.annotation.types;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tree_node")
@Getter
@Setter
public class TreeNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String node_data;

    @ManyToOne
    @JoinColumn(name = "DecisionTreeAnnotationID")
    private DecisionTreeEntity decisionTree;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TreeNode parent;

    @OneToMany(mappedBy = "parent")
    private List<TreeNode> children = new ArrayList<>();


}