package com.cjk.lyrics.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnkiExportDto {
    private String trackId;
    private List<AnkiCardDto> cards;
}
