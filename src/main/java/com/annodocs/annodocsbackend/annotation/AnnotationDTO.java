package com.annodocs.annodocsbackend.annotation;

import com.annodocs.annodocsbackend.annotation.types.FormField;
import com.annodocs.annodocsbackend.annotation.types.TreeNode;
import com.annodocs.annodocsbackend.gesetz.paragraph.paragraphEntity;
import com.annodocs.annodocsbackend.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class AnnotationDTO {

    @Setter
    @Getter
    private String type;

    @Setter
    @Getter
    private String ersteller;

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

    @Setter
    @Getter
    private UserEntity user;

    @Setter
    @Getter
    private paragraphEntity paragraph;

    @Setter
    @Getter
    private Set<TreeNode> children;

    @Setter
    @Getter
    private TreeNode parent;

    @Setter
    @Getter
    private Set<FormField> formFields;

    @Setter
    @Getter
    private String bpmnXml;

}
