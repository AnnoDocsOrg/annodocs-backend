package com.annodocs.annodocsbackend.gesetz.paragraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/paragraphs")
public class paragraphController {

    @Autowired
    private paragraphService paragraphService;

    @GetMapping("/{id}")
    public ResponseEntity<paragraphEntity> getParagraphWithAnnotations(@PathVariable Long id) {
        Optional<paragraphEntity> paragraph = paragraphService.getParagraph(id);
        return paragraph.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
