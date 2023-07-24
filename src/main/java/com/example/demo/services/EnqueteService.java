package com.example.demo.services;

import com.example.demo.dto.CriarEnqueteDTO;
import com.example.demo.dto.EnqueteDTO;
import com.example.demo.dto.OpcaoDTO;
import com.example.demo.models.Enquete;
import com.example.demo.models.Opcao;
import com.example.demo.repositories.EnqueteRepository;
import com.example.demo.repositories.OpcaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EnqueteService {

    @Autowired
    private EnqueteRepository enqueteRepository;
    @Autowired
    private OpcaoRepository opcaoRepository;
    @Autowired
    private OpcaoService opcaoService;

    public EnqueteDTO criarEnquete(CriarEnqueteDTO criarEnqueteDTO) {
        Enquete enquete = new Enquete();
        enquete.setPergunta(criarEnqueteDTO.pergunta());

        // Mapear as opções do DTO para entidades
        Enquete finalEnquete = enquete;
        List<Opcao> opcoes = IntStream.range(0, criarEnqueteDTO.opcoes().size())
                .mapToObj(i -> {
                    Opcao opcao = new Opcao();
                    opcao.setTexto(criarEnqueteDTO.opcoes().get(i).texto());
                    opcao.setEnquete(finalEnquete);
                    opcao.setIndex(i);
                    return opcao;
                })
                .collect(Collectors.toList());

        enquete.setOpcoes(opcoes);
        enquete = enqueteRepository.save(enquete);

        return this.convertToDTO(enquete);
    }

    public List<EnqueteDTO> listarEnquetes() {
        List<Enquete> enquetes = enqueteRepository.findAll();
        return enquetes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EnqueteDTO convertToDTO(Enquete enquete) {
        List<OpcaoDTO> opcoesDTO = enquete.getOpcoes().stream()
                .map(opcao -> new OpcaoDTO(opcao.getId(), opcao.getTexto(), opcao.getNumeroVotos(), opcao.getIndex()))
                .collect(Collectors.toList());

        return new EnqueteDTO(enquete.getId(), enquete.getPergunta(), opcoesDTO);
    }

    public EnqueteDTO votarNaOpcao(Long enqueteId, int opcaoIndex) {
        Enquete enquete = enqueteRepository.findById(enqueteId)
                .orElseThrow(() -> new EntityNotFoundException("Enquete não encontrada com o ID: " + enqueteId));

        //obter a opção pelo index, não por id
        Opcao opcao = enquete.getOpcoes().stream()
                .filter(op -> Objects.equals(op.getIndex(), opcaoIndex))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Opção não encontrada com o ID: " + opcaoIndex));

        opcao.setNumeroVotos(opcao.getNumeroVotos() + 1);
        enqueteRepository.save(enquete);

        return convertToDTO(enquete);
    }

    public void excluirEnquete(Long enqueteId){
        Enquete enquete = enqueteRepository.findById(enqueteId).orElseThrow(() -> new EntityNotFoundException("Enquete não encontrada com o ID: " + enqueteId));
        enqueteRepository.delete(enquete);
    }
}
