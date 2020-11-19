/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pdv.bean;

import br.pdv.dao.PosDao;
import br.pdv.model.PosTeste;
import br.pdv.util.pdvException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;





/**
 *
 * @author julianos
 */
//
@SuppressWarnings("deprecation")
@ManagedBean(name = "posBeanTeste")
@SessionScoped
public class PosBeanTeste implements Serializable {

    private PosTeste posTeste = new PosTeste();
    private PosDao dao = new PosDao();
    private String itemBandeira;
    private String itemLogico;

    public PosTeste getPosTeste() {
        return posTeste;
    }

    public void setPosTeste(PosTeste posTeste) {
        this.posTeste = posTeste;
    }



    public PosDao getDao() {
        return dao;
    }

    public void setDao(PosDao dao) {
        this.dao = dao;
    }

    public String getItemBandeira() {
        return itemBandeira;
    }

    public void setItemBandeira(String itemBandeira) {
        this.itemBandeira = itemBandeira;
    }

    public String getItemLogico() {
        return itemLogico;
    }

    public void setItemLogico(String itemLogico) {
        this.itemLogico = itemLogico;
    }

 

 
    public String dados(){
        System.out.println("Logico: " + itemLogico + " /n Bandeira: " + itemBandeira);
        return "Logico: " + itemLogico + " /n Bandeira: " + itemBandeira;
    }

    public String novoPos(){
        this.posTeste = new PosTeste();
        return "posTeste?faces-redirect=true";
    }
    
    public String salvar() throws pdvException {
      
        
        if (this.posTeste.getId() == null) {
           // Bandeira band = Bandeira.valueOf(itemBandeira);
           System.out.println("Teste N° Lógico: " + this.posTeste.getLogico()); 
           System.out.println("Teste itemBandeira: " + this.posTeste.getBandeira());
            
            //pos.setBand(this.itemBandeira);
            //posTeste.setLogico(getItemLogico());
            //posTeste.setBandeira(getItemBandeira());
            dao.salvarTeste(posTeste);
        } else {
           // dao.editar(pos);
        }
        this.posTeste = new PosTeste();
        
        return "listaPos?faces-redirect=true";
    
    }

   /* public String editar(Pos pos) throws pdvException {

        this.pos = pos;

        Boolean on = true;
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

        flash.put("pos", pos);

        return "formPos?faces-redirect=true";
    }

    public void carregar() throws pdvException {
       
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        Boolean on = (Boolean) flash.get("on");
        if (on != null && on == true) {
            pos = (Pos) flash.get("pos");        
        }
    }
    
    public void remover(Pos pos) throws pdvException{
        dao.excluir(pos);
    }   
    

    public List<Pos> getListaPos() throws pdvException {
        return dao.listaPos();
    }
    
    public BandeiraCartao[] getBandeiras(){
        return BandeiraCartao.values();
    }
    
    public List<String> getBandereirasPos(){
        return Arrays.asList("cielo", "rede", "getnet");
    }*/
  


}
