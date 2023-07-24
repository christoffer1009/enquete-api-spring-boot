package com.example.demo.services;

import com.example.demo.dto.OpcaoDTO;
import com.example.demo.models.Opcao;
import com.example.demo.repositories.OpcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpcaoService {
    @Autowired
    private OpcaoRepository opcaoRepository;

    public OpcaoDTO criarOpcao(OpcaoDTO opcaoDTO) {
        Opcao opcao = new Opcao();
        opcao.setTexto(opcaoDTO.texto());
        opcao.setNumeroVotos(opcaoDTO.numeroVotos());

        opcao = opcaoRepository.save(opcao);
        return this.convertToDTO(opcao);
    }

    public List<OpcaoDTO> listarOpcoes() {
        List<Opcao> opcoes = opcaoRepository.findAll();
        return opcoes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OpcaoDTO convertToDTO(Opcao opcao) {
        return new OpcaoDTO(opcao.getId(), opcao.getTexto(), opcao.getNumeroVotos(), opcao.getIndex());
    }

}
