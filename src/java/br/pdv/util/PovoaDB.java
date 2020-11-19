/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pdv.util;

import br.pdv.bean.ComputerBean;
import br.pdv.dao.ComputerDao;
import br.pdv.dao.LogDao;
import br.pdv.dao.PdvDao;
import br.pdv.dao.PosDao;
import br.pdv.dao.UsuarioDao;
import br.pdv.model.Computer;
import br.pdv.model.Log;
import br.pdv.model.Pdv;
import br.pdv.model.Pos;
import br.pdv.model.Usuario;
import br.pdv.model.enums.Bandeira;
import br.pdv.model.enums.Chip;
import br.pdv.model.enums.Locale;
import br.pdv.model.enums.ModeloPos;
import br.pdv.model.enums.TipoLog;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

/**
 *
 * @author julianos
 */
public class PovoaDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws pdvException {
        // TODO code application logic here

        //EntityManager em = JPAUtil.getEntityManager();
       // em.getTransaction().begin();

        System.out.println("Incio JPA");
        
// dao.salvar(pos);
        //List<Pos> poss = dao.listaPos();
        //for(Pos p : poss){
          //  System.out.println("Id: " + p.getId() + " - Nº Lógico: " + p.getLogico());
        //}
        
        
        List<Usuario> usuarios = new UsuarioDao().listaUsuarios();
        for(Usuario u: usuarios){
            System.out.println(u.getId() + " - " + u.getNome() + " - " + u.getSobrenome()+ " - " + u.getCelular());
        }
        
        System.out.print("Usuario por Id: ");
        Usuario user = new UsuarioDao().usuarioById(3);
        System.out.println(user.getNome());
        
        PosDao dao = new PosDao();
        //Pos pos = new Pos("785RGT", Bandeira.getnet, Chip.Oi, ModeloPos.MOVE5000, Locale.Website, "85965421", null, null, LocalDateTime.now(), 0);
        //Usuario u = usuarios.get(1);
       // pos.setUsuario(u);
       // dao.salvar(pos);
       Pos pos = new Pos();
        List<Pos> poss = dao.listaPosMatriz();
        System.out.println(poss.size());
        List<Pos> qtdCielo = poss.stream().filter(x -> (x.getBandeira() == Bandeira.cielo)).collect(Collectors.toList());
        System.out.println(qtdCielo.size());
        //Log log = em.find(Log.class, 1);
       //Computer pc = em.find(Computer.class, 1);
      // Computer pc = new Computer();
      // Pdv pdv = em.find(Pdv.class, 2);
     //  pc.setNome("caixa02");
     //  pc.setProcessador("core i8");
     //  pc.setPdv(pdv);
     //   System.out.println(pc.toString());
       // System.out.println(log.toString());
        
       // Computer pc = new ComputerDao().listaPC().get(1);
       // System.out.println("---- LOGs ----");
        
      //  List<Log> logs = new LogDao().listaLog();
      //  logs.add(new Log("Oi", LocalDateTime.now(), pc));
      //  logs.add(new Log("Porque", LocalDateTime.now(), pc));
      //  for(Log l : logs){
      //      System.out.println(l.toString());
      //  }
        
      //  System.out.println("Quatidade Logs: " + logs.size());
        
      /*  System.out.println("---- PDVS -----");
        List<Pdv> pdvs = new PdvDao().listaPdv();
        System.out.println(pdvs.get(0));
        */
      /*
        List<TipoLog> tipoLogs = Arrays.asList(TipoLog.values());
        int i = 0;
        for(TipoLog t: tipoLogs){
            System.out.println(t.toString());
        }
        /*Log[] Arraylog = new Log[]{new Log(TipoLog.Banco, "Erro", LocalDateTime.now()),
            new Log(TipoLog.Conexão, "Falha", LocalDateTime.now()),
            new Log(TipoLog.Hardware, "Quebrou", LocalDateTime.now())};

        Computer comp = new Computer("caixa01", "Core i5", "20Gb DDR4 ", "2Tb SSD", "Blz", 0, Boolean.TRUE);
        ComputerDao pcdao = new ComputerDao();
        pcdao.salvar(comp);
        LogDao ldao = new LogDao();
        for (Log log : Arraylog) {
            log.setPc(comp);
            ldao.salvar(log);

        }*/

        //em.close();
    }

}
