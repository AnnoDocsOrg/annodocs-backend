package com.annodocs.annodocsbackend.JSONimport.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GesetzDTO {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String anlage;

    @Getter
    @Setter
    private List<ParagraphDTO> paragraphen;

}
