package com.cjk.lyrics.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnkiCardDto {
    private String front;
    private String back;
    private List<String> tags;
    private String example;
}
