package br.pdv.model;

import java.io.Serializable;


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

import br.pdv.model.enums.TipoLog;
import java.time.LocalDateTime;

@Entity
@Table(name = "logg")
public class Log implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipoLog")
    private String TipoLog;
    /*@Enumerated(EnumType.STRING)
    private TipoLog tipoLog;*/

    private String descricao;

    @Column(name = "datahora")
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "idpc", referencedColumnName = "id")
    private Computer pc;

    public Log() {
    }    

    public Log(String descricao, LocalDateTime data) {
        this.descricao = descricao;
        this.data = data;
    }

    public Log(String descricao, LocalDateTime data, Computer pc) {
        this.descricao = descricao;
        this.data = data;
        this.pc = pc;
    }
    
    
   public Log(String tipoLog, String descricao, LocalDateTime data) {
        this.TipoLog = tipoLog;
        this.descricao = descricao;
        this.data = data;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   /* public TipoLog getTipoLog() {
        return tipoLog;
    }

    public void setTipoLog(TipoLog tipoLog) {
        this.tipoLog = tipoLog;
    }*/

    public String getTipoLog() {
        return TipoLog;
    }

    public void setTipoLog(String TipoLog) {
        this.TipoLog = TipoLog;
    }
    
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Computer getPc() {
        return pc;
    }

    public void setPc(Computer pc) {
        this.pc = pc;
    }

    @Override
    public String toString() {
        return "Log{" + "id=" + id  + ", descricao=" + descricao + ", data=" + data + ", pc=" + pc.getNome() + '}';
    }
    
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
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
        Log other = (Log) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
