package servico;

import dao.AgendaDAO;
import dao.AgendaDAOImpl;
import dominio.Agenda;
import dominio.Paciente;
import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.sql.Connection;
import java.util.List;
import static servico.ServicoAbstrato.obterConnection;

public class AgendaServico extends ServicoAbstrato {

    public static void marcarConsulta(Agenda age)throws SystemException, DuplicadoException{
         Connection conn = obterConnection();
         AgendaDAO ad = new AgendaDAOImpl(conn);
         ad.inserir(age);
    }
    
    public static List<Agenda> listaAgenda(String dada, int matMedico) throws SystemException {
        Connection conn = obterConnection();
        AgendaDAO ad = new AgendaDAOImpl(conn);
        List<Agenda> res = ad.listarAgendaMed(dada, matMedico);
        close(conn);
        return res;
    }
    
    public static void alterarAtendimetno(Agenda age)throws SystemException {
        Connection conn = obterConnection();
        AgendaDAO ad = new AgendaDAOImpl(conn);
        ad.alterarAtendimento(age);
    }
    
     public static List<Agenda> pesquisarAgenda(Agenda filtroAge, Paciente filtroPac) throws SystemException {
      if(filtroAge == null){
          filtroAge = new Agenda();
      }
      
      if(filtroPac == null){
          filtroPac = new Paciente();
      }
        Connection conn = obterConnection();
        AgendaDAO ad = new AgendaDAOImpl(conn);
        List<Agenda> res = ad.PesquisarAgenda(filtroAge, filtroPac);
        close(conn);
        return res;
    }
     
     public static List<Agenda> pesquisarAgendaAtendimetno(Agenda filtroAge, Paciente filtroPac) throws SystemException {
      if(filtroAge == null){
          filtroAge = new Agenda();
      }
      
      if(filtroPac == null){
          filtroPac = new Paciente();
      }
        Connection conn = obterConnection();
        AgendaDAO ad = new AgendaDAOImpl(conn);
        List<Agenda> res = ad.PesquisarAgendaAtendimento(filtroAge, filtroPac);
        close(conn);
        return res;
    }
}
