/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pdv.model;

import br.pdv.model.enums.Bandeira;
import br.pdv.model.enums.Locale;
import br.pdv.model.enums.Chip;
import br.pdv.model.enums.ModeloPos;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author julianos
 */
@Entity
@Table(name = "pos")
public class Pos implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numlogico")
    private String logico;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "bandeira")
    private Bandeira bandeira;

    @Enumerated(EnumType.STRING)
    private Chip chip;

    @Enumerated(EnumType.STRING)
    private ModeloPos modelo;

    @Enumerated(EnumType.STRING)
    private Locale locale;

    private String ns;

    private String obs;
    
    private String motivo;

    private Integer substituta;
    
    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    private Usuario usuario;

    private LocalDateTime incluida;

    private Integer filial;

    private boolean ativo = true;

    public Pos() {
    }

    public Pos(String logico, Bandeira bandeira, Chip chip, ModeloPos modelo, Locale locale, String ns, String obs, Integer substituta, LocalDateTime incluida, Integer filial) {
        this.logico = logico;
        this.bandeira = bandeira;
        this.chip = chip;
        this.modelo = modelo;
        this.locale = locale;
        this.ns = ns;
        this.obs = obs;
        this.substituta = substituta;
        this.incluida = incluida;
        this.filial = filial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogico() {
        return logico;
    }

    public void setLogico(String logico) {
        this.logico = logico;
    }

    public Bandeira getBandeira() {
        return bandeira;
    }

    public void setBandeira(Bandeira bandeira) {
        this.bandeira = bandeira;
    }

    public Chip getChip() {
        return chip;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
    }

    public ModeloPos getModelo() {
        return modelo;
    }

    public void setModelo(ModeloPos modelo) {
        this.modelo = modelo;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public Integer getSubstituta() {
        return substituta;
    }

    public void setSubstituta(Integer substituta) {
        this.substituta = substituta;
    }

    public LocalDateTime getIncluida() {
        return incluida;
    }

    public void setIncluida(LocalDateTime incluida) {
        this.incluida = incluida;
    }

    public Integer getFilial() {
        return filial;
    }

    public void setFilial(Integer filial) {
        this.filial = filial;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pos other = (Pos) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
