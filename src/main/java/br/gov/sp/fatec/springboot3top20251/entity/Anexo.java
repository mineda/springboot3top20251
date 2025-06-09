package br.gov.sp.fatec.springboot3top20251.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ane_anexo")
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ane_id")
    private Long id;

    @Column(name = "ane_texto")
    private String texto;

    @Column(name = "ane_tipo")
    private String tipo;

    @Column(name = "ane_data_hora")
    private LocalDateTime dataHora;
    
    @ManyToOne
    @JoinColumn(name = "ane_tra_id")
    private Trabalho trabalho;

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
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Trabalho getTrabalho() {
        return trabalho;
    }
    
    public void setTrabalho(Trabalho trabalho) {
        this.trabalho = trabalho;
    }
}
