package com.example.demo.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Enquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pergunta;

    @OneToMany(mappedBy = "enquete", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Opcao> opcoes = new ArrayList<>();

    public Enquete(Long id, String pergunta) {
        this.id = id;
        this.pergunta = pergunta;
    }

    public Enquete() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public List<Opcao> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<Opcao> opcoes) {
        this.opcoes = opcoes;
    }
}
