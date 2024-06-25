package com.annodocs.annodocsbackend.JSONimport.controller;

import com.annodocs.annodocsbackend.JSONimport.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/gesetze")
public class ImportController {

    @Autowired
    private ImportService importService;

    @PostMapping("/import")
    public String importData(@RequestParam String filePath) {
        try {
            importService.importData(filePath);
            return "Import erfolgreich!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Fehler beim Import: " + e.getMessage();
        }
    }

}
