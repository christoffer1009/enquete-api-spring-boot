package com.example.demo.dto;

import java.util.List;

public record CriarEnqueteDTO(Long id, String pergunta, List<CriarOpcaoDTO> opcoes) {
}
