package com.annodocs.annodocsbackend.JSONimport.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ParagraphDTO {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String paragraph;

    @Getter
    @Setter
    private List<String> text;

    @Getter
    @Setter
    private String footnotes;

    @Getter
    @Setter
    private String notes;

    @Getter
    @Setter
    private String url;

}
