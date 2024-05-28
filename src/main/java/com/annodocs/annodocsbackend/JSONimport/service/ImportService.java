package com.annodocs.annodocsbackend.JSONimport.service;

import com.annodocs.annodocsbackend.JSONimport.DTOs.GesetzDTO;
import com.annodocs.annodocsbackend.JSONimport.DTOs.ParagraphDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.annodocs.annodocsbackend.gesetz.*;
import com.annodocs.annodocsbackend.gesetz.paragraph.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ImportService {

    private static final Logger LOGGER = Logger.getLogger(ImportService.class.getName());

    @Autowired
    private gesetzRepository gesetzRepository;

    @Autowired
    private paragraphRepository paragraphRepository;

    @Transactional
    public void importData(String filePath) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                LOGGER.severe("File not found: " + filePath);
                throw new IOException("File not found: " + filePath);
            }
            LOGGER.info("Reading file: " + filePath);
            GesetzDTO gesetzDTO = objectMapper.readValue(new File(filePath), GesetzDTO.class);

            gesetzEntity gesetz = new gesetzEntity();
            gesetz.setName(gesetzDTO.getName());
            gesetz.setAnlage(gesetzDTO.getAnlage());

            List<paragraphEntity> paragraphen = new ArrayList<>();
            for(ParagraphDTO paragraphDTO : gesetzDTO.getParagraphen()){
                paragraphEntity paragraph = new paragraphEntity();
                paragraph.setGesetz(gesetz);
                paragraph.setTitel(paragraphDTO.getTitle());
                paragraph.setParagraph(paragraphDTO.getParagraph());
                LOGGER.info("Trying to Join Strings:");
                paragraph.setText(String.join(" ", paragraphDTO.getText()));
                LOGGER.info("Success in joining Strings");
                paragraph.setFootnotes(paragraphDTO.getFootnotes());
                paragraph.setNotes(paragraphDTO.getNotes());
                paragraph.setUrl(paragraphDTO.getUrl());
                paragraphen.add(paragraph);
            }
            gesetz.setParagraphen(paragraphen);
            gesetzRepository.save(gesetz);
        } catch (IOException e) {
            LOGGER.severe("Error reading the file: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.severe("Error processing the data: " + e.getMessage());
            throw new RuntimeException("Error processing the data", e);
        }
    }

}
