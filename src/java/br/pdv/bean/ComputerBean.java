/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pdv.bean;

import br.pdv.dao.ComputerDao;
import br.pdv.dao.LogDao;
import br.pdv.dao.PdvDao;
import br.pdv.dao.PosDao;
import br.pdv.model.Computer;
import br.pdv.model.Log;
import br.pdv.model.Pdv;
import br.pdv.model.PosTeste;
import br.pdv.model.enums.TipoLog;
import br.pdv.util.pdvException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author jsser
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "computadorBean")
@SessionScoped
public class ComputerBean implements Serializable {

    private Computer pc = new Computer();
    private List<Log> logsPc = new ArrayList<>();
    private Log log = new Log();
    private Pdv pdv = new Pdv();

    public ComputerDao dao = new ComputerDao();
    public LogDao daoLog = new LogDao();
    public PdvDao daoPdv = new PdvDao();

    private boolean logEstaVazio = true;
    private String logItem;
    private String logItemTipo;
    private String windows;

    private Integer idPdv;

    public boolean isLogEstaVazio() {
        return logEstaVazio;
    }

    public void setLogEstaVazio(boolean logEstaVazio) {
        this.logEstaVazio = logEstaVazio;
    }

    public Computer getPc() {
        return pc;
    }

    public void setPc(Computer pc) {
        this.pc = pc;
    }

    public List<Log> getLogsPc() {
        return logsPc;
    }

    public void setLogsPc(List<Log> logsPc) {
        this.logsPc = logsPc;
    }

    public String getLogItem() {
        return logItem;
    }

    public void setLogItem(String logItem) {
        this.logItem = logItem;
    }

    public String getLogItemTipo() {
        return logItemTipo;
    }

    public void setLogItemTipo(String logItemTipo) {
        this.logItemTipo = logItemTipo;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Integer getIdPdv() {
        return idPdv;
    }

    public void setIdPdv(Integer idPdv) {
        this.idPdv = idPdv;
    }

    public Pdv getPdv() {
        return pdv;
    }

    public void setPdv(Pdv pdv) {
        this.pdv = pdv;
    }

    public String getWindows() {
        return windows;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }
    
    

    public String gravarPc() throws pdvException {
        //pc.setLogs(this.logsPc);

        pdv = daoPdv.buscarPorId(this.idPdv);
        pc.setPdv(pdv);       

        if (!logsPc.isEmpty()) {

            if (pc.getId() == null) {
                // pc.setAtivo(Boolean.TRUE);
                dao.salvar(pc);
            } else {
                dao.editar(pc);
            }
            for (Log l : logsPc) {
                l.setPc(pc);
                daoLog.salvar(l);
            }

            Computer pcTemp = pc;
            this.pc = new Computer();
            this.logsPc = new ArrayList<>();
            if (pcTemp.getPdv().getFilial() == 0) {
                return "index?faces-redirect=true";
            } else {
                return "caixaFilial?faces-redirect=true";
            }
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage("Log",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira Log de inclusão!", ""));
            return "";
        }
    }

    public String editarPC(Computer pc) throws pdvException {
        this.pc = pc;
        this.logsPc = daoLog.listaLogs(pc.getId());

        Boolean on = true;
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

        flash.put("pc", this.pc);
        flash.put("logs", logsPc); //novo
        flash.put("pdv", pc.getPdv().getId());
        //flash.put("so", windows);
        flash.put("on", on);

        if (logsPc.isEmpty()) {
            logEstaVazio = true;
        } else {
            logEstaVazio = false;
        }
        if (pc.getPdv().getFilial() == 0) {
            return "formCaixa?faces-redirect=true";
        } else {
            return "formCaixaFilial?faces-redirect=true";
        }
    }

    public void carregar() throws pdvException {

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        Boolean on = (Boolean) flash.get("on");
        if (on != null && on == true) {
            this.pc = (Computer) flash.get("pc");
            this.logsPc = (List<Log>) flash.get("logs"); //novo
            this.idPdv = (Integer) flash.get("pdv");
           // this.windows = (String) flash.get("so");

            pdv = daoPdv.buscarPorId(idPdv);

            pc.setPdv(pdv);
        }
    }

    public void apagarLos(Log logs) throws pdvException {
        for (Log l : logsPc) {
            if (l.equals(logs)) {
                daoLog.excluir(logs);
            }
        }

        this.logsPc.remove(logs);

        if (logsPc.isEmpty()) {
            setLogEstaVazio(true);
        }
    }

    public void desativarPC(Computer pc) throws pdvException {
        // Computer pcTemp = pc;
        dao.desativarPc(pc);
        /*if(pcTemp.getPdv().getFilial() == 0 ){
            return "index?faces-redirect=true";
        }else{
            return "caixaFilial?faces-redirect=true";
        }*/
    }

    public String excluircPC(Computer pc) throws pdvException {
        Computer pcTemp = pc;
        dao.excluir(pc);
        if (pcTemp.getPdv().getFilial() == 0) {
            return "index?faces-redirect=true";
        } else {
            return "caixaFilial?faces-redirect=true";
        }
    }

    public List<Computer> getComputersDesativados() throws pdvException {
        return dao.listaPC();
    }

    public List<Computer> getComputersMatriz() throws pdvException {
        return dao.listaPcMatriz();
    }

    public List<Computer> getComputersFilial() throws pdvException {
        return dao.listaPcFilial();
    }

    public String novoPC() {
        this.pc = new Computer();
        this.logsPc = new ArrayList<>();
        return "formCaixa?faces-redirect=true";
    }

    public String novoPcFilial() {
        this.pc = new Computer();
        this.logsPc = new ArrayList<>();
        return "formCaixaFilial?faces-redirect=true";
    }

    public List<TipoLog> getTipoLogs() {

        List<TipoLog> tipoLogs = Arrays.asList(TipoLog.values());
        return tipoLogs;
    }

    public List<String> getTiposLogs() {
        return Arrays.asList("Banco", "caixa Incluir", "caixa Troca", "caixa Baixa", "Conexão", "Hardware",
                "SASIII", "Rede", "Site Receita", "Stress", "Windows");
    }
    
    public List<String> getSO(){
        return Arrays.asList("win7 pro x86","win7 pro x64","win10 pro x86", "win10 pro x64");
    }

    public void adicionarLog() throws pdvException {
        // this.log.setDescricao(this.logItem);
        // this.log.setData(LocalDateTime.now());
        // this.logsPc.add(log);
        this.logsPc.add(new Log(logItemTipo, logItem, LocalDateTime.now()));
        this.logItemTipo = null;
        this.logItem = null;
        setLogEstaVazio(false);
    }

    public List<Pdv> getListaPdvs() throws pdvException {
        return daoPdv.listaPdv();
    }

    public List<Pdv> getListaPdvsFilial() throws pdvException {
        return daoPdv.listaPdvFilial();
    }
    
    public Integer getQtdLogPc(Computer computer) throws pdvException{
        return daoLog.listaLogs(computer.getId()).size();
    }

    public void ordenarLogs(String ordem) {
        if (ordem.equals("az")) {
            logsPc.sort(Comparator.comparing(Log::getTipoLog));
        } else {
            logsPc.sort(Comparator.comparing(Log::getTipoLog).reversed());
        }
    }

    public void atualizarPdv(AjaxBehaviorEvent e) throws pdvException {
        pdv = daoPdv.buscarPorId(this.idPdv);
    }
    
    
    //--------------------------------
    
        private PosTeste posTeste = new PosTeste();
    private PosDao daoPos = new PosDao();
    private String itemBandeira;
    private String itemLogico;

    public PosTeste getPosTeste() {
        return posTeste;
    }

    public void setPosTeste(PosTeste posTeste) {
        this.posTeste = posTeste;
    }



    public PosDao getDao() {
        return daoPos;
    }

    public void setDao(PosDao dao) {
        this.daoPos = dao;
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
    
    public String salvarTeste() throws pdvException {
      
        
        if (this.posTeste.getId() == null) {
           // Bandeira band = Bandeira.valueOf(itemBandeira);
           System.out.println("Teste N° Lógico: " + this.posTeste.getLogico()); 
           System.out.println("Teste itemBandeira: " + this.posTeste.getBandeira());
            
            //pos.setBand(this.itemBandeira);
            //posTeste.setLogico(getItemLogico());
            //posTeste.setBandeira(getItemBandeira());
            daoPos.salvarTeste(this.posTeste);
        } else {
           // dao.editar(pos);
        }
        this.posTeste = new PosTeste();
        
        return "listaPos?faces-redirect=true";
    }


}
