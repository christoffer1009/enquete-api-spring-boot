package com.example.demo.controllers;

import com.example.demo.dto.CriarEnqueteDTO;
import com.example.demo.dto.EnqueteDTO;
import com.example.demo.services.EnqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enquetes")
public class EnqueteController {
    @Autowired
    private EnqueteService enqueteService;

    @GetMapping
    public List<EnqueteDTO> listarEnquetes() {
        return enqueteService.listarEnquetes();
    }

    @PostMapping
    public EnqueteDTO criarEnquete(@RequestBody CriarEnqueteDTO enqueteDTO) {
        return enqueteService.criarEnquete(enqueteDTO);
    }

    @PostMapping("/{enqueteId}/votar/{opcaoIndex}")
    public EnqueteDTO votarNaOpcao(@PathVariable Long enqueteId, @PathVariable int opcaoIndex) {
        return enqueteService.votarNaOpcao(enqueteId, opcaoIndex);
    }

    @DeleteMapping("/{enqueteId}")
    public ResponseEntity<Void> deletarEnquete(@PathVariable Long enqueteId) {
        enqueteService.excluirEnquete(enqueteId);
        return ResponseEntity.noContent().build();
    }
}