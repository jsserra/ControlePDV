package br.pdv.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pc")
public class Computer implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    
    private String tag;

    private String processador;

    private String memoria;

    private String hd;
    
    private String offboard;
    
    @Column(name="so")
    private String so;

    private String obs;
    
    private boolean ativo = true;

    @OneToMany(mappedBy = "pc", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Log> logs;
    
    @OneToOne
    @JoinColumn(name="idpdv")
    private Pdv pdv;

    public Computer() {
    }

    public Computer(boolean ativo, Pdv pdv) {
        this.ativo = ativo;
        this.pdv = pdv;
    }

    public Computer(String nome, String tag, String processador, String memoria, String hd, String offboard, String so, String obs, Set<Log> logs, Pdv pdv) {
        this.nome = nome;
        this.tag = tag;
        this.processador = processador;
        this.memoria = memoria;
        this.hd = hd;
        this.offboard = offboard;
        this.so = so;
        this.obs = obs;
        this.logs = logs;
        this.pdv = pdv;
    }    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }    
    
    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    public String getOffboard() {
        return offboard;
    }

    public void setOffboard(String offboard) {
        this.offboard = offboard;
    }  

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }       
    
    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    } 

    public Pdv getPdv() {
        return pdv;
    }

    public void setPdv(Pdv pdv) {
        this.pdv = pdv;
    }   

    @Override
    public String toString() {
        return "Computer{" + "nome=" + nome + ", processador=" + processador + ", memoria=" + memoria + ", hd=" + hd + ", obs=" + obs + ", ativo=" + ativo + ", pdv=" + pdv.getNome() + '}';
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
        Computer other = (Computer) obj;
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
