package com.example.demo.models;

import jakarta.persistence.*;

@Entity
public class Opcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;
    private int numeroVotos = 0;

    private int index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enquete_id")
    private Enquete enquete;

    public Opcao(Long id, String texto, int numeroVotos, Enquete enquete, int index) {
        this.id = id;
        this.texto = texto;
        this.numeroVotos = numeroVotos;
        this.enquete = enquete;
        this.index = index;

    }

    public Opcao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getNumeroVotos() {
        return numeroVotos;
    }

    public void setNumeroVotos(int numeroVotos) {
        this.numeroVotos = numeroVotos;
    }

    public Enquete getEnquete() {
        return enquete;
    }

    public void setEnquete(Enquete enquete) {
        this.enquete = enquete;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
