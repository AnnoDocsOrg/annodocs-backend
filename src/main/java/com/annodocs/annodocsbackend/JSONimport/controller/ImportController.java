package com.annodocs.annodocsbackend.JSONimport.controller;

import com.annodocs.annodocsbackend.JSONimport.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ImportController {

    @Autowired
    private ImportService importService;

    @GetMapping("/import")
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
