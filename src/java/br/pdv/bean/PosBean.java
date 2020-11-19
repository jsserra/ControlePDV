/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pdv.bean;

import br.pdv.dao.PosDao;
import br.pdv.dao.UsuarioDao;
import br.pdv.model.Pos;
import br.pdv.model.Usuario;
import br.pdv.model.enums.Bandeira;
import br.pdv.model.enums.Chip;
import br.pdv.util.pdvException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author julianos
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "posBean")
@SessionScoped
public class PosBean implements Serializable {

    private Pos pos = new Pos();
    private Usuario usuario = new Usuario();

    private PosDao dao = new PosDao();
    private UsuarioDao daoUsuario = new UsuarioDao();

    private Bandeira band;
    private Chip chip;

    private String item;
    private String msg;
    private Integer usuarioId;

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public PosDao getDao() {
        return dao;
    }

    public void setDao(PosDao dao) {
        this.dao = dao;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UsuarioDao getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(UsuarioDao daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Bandeira getBand() {
        return band;
    }

    public void setBand(Bandeira band) {
        this.band = band;
    }

    public Chip getChip() {
        return chip;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
    }

    public String novoPos() {
        pos = new Pos();

        return "formPos?faces-redirect=true";
    }

    public String salvar() throws pdvException {

        usuario = daoUsuario.usuarioById(this.usuarioId);
        pos.setUsuario(usuario);
        pos.setBandeira(band);
        pos.setChip(chip);

        if (this.pos.getId() == null) {
            dao.salvar(this.pos);
        } else {
            dao.editar(pos);
        }
        
        pos = new Pos();

        return "listaPos?faces-redirect=true";
    }

    public String editar(Pos pos) throws pdvException {

        this.pos = pos;

        Boolean on = true;
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

        flash.put("pos", pos);
        flash.put("bandeira", pos.getBandeira());
        flash.put("chip", pos.getChip());
        flash.put("user", pos.getUsuario().getId());
        flash.put("on", on);

        return "formPos?faces-redirect=true";
    }

    public void carregar() throws pdvException {

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        Boolean on = (Boolean) flash.get("on");
        if (on != null && on == true) {
            this.pos = (Pos) flash.get("pos");
            this.band = (Bandeira) flash.get("bandeira");
            this.chip = (Chip) flash.get("chip");
            this.usuarioId = (Integer) flash.get("user");

            usuario = daoUsuario.usuarioById(usuarioId);
            pos.setBandeira(band);
            pos.setChip(chip);
            pos.setUsuario(usuario);
        }
    }

    public String remover(Pos pos) throws pdvException {
        dao.excluir(pos);
        return "listaPos?faces-redirect=true";
    }

    public String desativar(Pos pos) throws pdvException {
        dao.dasativar(pos);
        return "listaPos?faces-redirect=true";
    }

    public List<Pos> getListaPosMatriz() throws pdvException {
        return dao.listaPosMatriz();
    }

    public List<Pos> getListaPosFilial() throws pdvException {
        return dao.listaPosFilial();
    }

    public List<Usuario> getListaUsuarios() throws pdvException {
        return daoUsuario.listaUsuarios();
    }
    
    public Integer getQtdPosMatriz() throws pdvException{
        return dao.listaPosMatriz().size();
    }
    
    public Integer getQtdPosMatrizCielo() throws pdvException{        
        List<Pos> qtdCielo = dao.listaPosMatriz().stream().filter(x -> (x.getBandeira() == Bandeira.cielo)).collect(Collectors.toList());
        return qtdCielo.size();
    }

    public Integer getQtdPosMatrizRede() throws pdvException{        
        List<Pos> qtdCielo = dao.listaPosMatriz().stream().filter(x -> (x.getBandeira() == Bandeira.rede)).collect(Collectors.toList());
        return qtdCielo.size();
    }
    
    public Integer getQtdPosMatrizGetnet() throws pdvException{        
        List<Pos> qtdCielo = dao.listaPosMatriz().stream().filter(x -> (x.getBandeira() == Bandeira.getnet)).collect(Collectors.toList());
        return qtdCielo.size();
    }
    
    public Integer getQtdPosFilial() throws pdvException{
        return dao.listaPosFilial().size();
    }

     public Integer getQtdPosFilialCielo() throws pdvException{        
        List<Pos> qtdCielo = dao.listaPosFilial().stream().filter(x -> (x.getBandeira() == Bandeira.cielo)).collect(Collectors.toList());
        return qtdCielo.size();
    }

    public Integer getQtdPosFilialRede() throws pdvException{        
        List<Pos> qtdCielo = dao.listaPosFilial().stream().filter(x -> (x.getBandeira() == Bandeira.rede)).collect(Collectors.toList());
        return qtdCielo.size();
    }
    
    public Integer getQtdPosFilialGetnet() throws pdvException{        
        List<Pos> qtdCielo = dao.listaPosFilial().stream().filter(x -> (x.getBandeira() == Bandeira.getnet)).collect(Collectors.toList());
        return qtdCielo.size();
    }
}
