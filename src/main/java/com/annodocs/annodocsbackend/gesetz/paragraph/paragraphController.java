package com.annodocs.annodocsbackend.gesetz.paragraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void setParagraph(@RequestBody paragraphEntity paragraph) {
        paragraphService.saveParagraph(paragraph);
    }

}
