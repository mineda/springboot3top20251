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
@Table(name = "wis_wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wis_id")
    private Long id;

    @Column(name = "wis_comentario", length = 100)
    private String comentario;

    @Column(name = "wis_data_hora_inclusao", nullable = false)
    private LocalDateTime dataHoraInclusao;

    @Column(name = "wis_nivel_interesse", nullable = false)
    private Integer nivelInteresse;

    @ManyToOne
    @JoinColumn(name = "wis_har_id")
    private Hardware hardware;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public void setDataHoraInclusao(LocalDateTime dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

    public Integer getNivelInteresse() {
        return nivelInteresse;
    }

    public void setNivelInteresse(Integer nivelInteresse) {
        this.nivelInteresse = nivelInteresse;
    }

    public Hardware getHardware() {
        return hardware;
    }
    
    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }
    
}
