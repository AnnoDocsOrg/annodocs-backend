package com.annodocs.annodocsbackend.annotation;

import com.annodocs.annodocsbackend.annotation.types.DecisionTreeEntity;
import com.annodocs.annodocsbackend.annotation.types.FormEntity;
import com.annodocs.annodocsbackend.annotation.types.ProcessModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annotations")
public class AnnotationController {

    @Autowired
    private AnnotationService annotationService;

    @PostMapping
    public void createAnnotation(@RequestBody AnnotationDTO annotationDTO) {
        switch (annotationDTO.getType()) {
            case "DecisionTree":
                DecisionTreeEntity decisionTree = new DecisionTreeEntity();

                decisionTree.setErsteller(annotationDTO.getErsteller());
                decisionTree.setAnnotationAbschnitte(annotationDTO.getAnnotationAbschnitte());
                decisionTree.setName(annotationDTO.getName());
                decisionTree.setDatum(annotationDTO.getDatum());
                decisionTree.setUser(annotationDTO.getUser());
                decisionTree.setParagraph(annotationDTO.getParagraph());
                decisionTree.setTreeNodes(annotationDTO.getChildren());

                annotationService.saveAnnotation(decisionTree);

                break;

            case "Form":
                FormEntity form = new FormEntity();

                form.setErsteller(annotationDTO.getErsteller());
                form.setAnnotationAbschnitte(annotationDTO.getAnnotationAbschnitte());
                form.setName(annotationDTO.getName());
                form.setDatum(annotationDTO.getDatum());
                form.setUser(annotationDTO.getUser());
                form.setParagraph(annotationDTO.getParagraph());

                annotationService.saveAnnotation(form);

                break;
            case "ProcessModel":
                ProcessModelEntity processmodel = new ProcessModelEntity();

                processmodel.setErsteller(annotationDTO.getErsteller());
                processmodel.setAnnotationAbschnitte(annotationDTO.getAnnotationAbschnitte());
                processmodel.setName(annotationDTO.getName());
                processmodel.setDatum(annotationDTO.getDatum());
                processmodel.setUser(annotationDTO.getUser());
                processmodel.setParagraph(annotationDTO.getParagraph());

                annotationService.saveAnnotation(processmodel);

                break;
        }
    }

    @GetMapping("/user/{userId}")
    public List<AnnotationEntity> getAnnotationsByUser(@PathVariable Long userId) {
        return annotationService.getAnnotationsByUserId(userId);
    }

}
