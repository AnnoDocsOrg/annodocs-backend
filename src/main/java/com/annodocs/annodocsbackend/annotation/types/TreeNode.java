package com.annodocs.annodocsbackend.annotation.types;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tree_nodes")
public class TreeNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    @Column(name = "node_data", nullable = false, columnDefinition = "TEXT")
    private String nodeData;

    @Setter
    @Getter
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TreeNode> children;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private TreeNode parent;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "decision_tree_id")
    private DecisionTreeEntity decisionTree;

}