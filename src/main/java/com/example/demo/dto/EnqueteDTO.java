package com.example.demo.dto;

import java.util.List;

public record EnqueteDTO(Long id, String pergunta, List<OpcaoDTO> opcoes) {
}
