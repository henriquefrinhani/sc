package dao;

import dominio.Agenda;
import dominio.Paciente;
import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.util.List;

public interface AgendaDAO {
	//teste
    public void inserir(Agenda age) throws SystemException, DuplicadoException;

    public void alterar(Agenda age) throws SystemException, DuplicadoException;
    
    public void alterarAtendimento(Agenda age) throws SystemException, DuplicadoException;

    public void excluir(Agenda age) throws SystemException;

    public List<Agenda> PesquisarAgenda(Agenda filtroAge, Paciente filtroPac) throws SystemException;

    public List<Agenda> PesquisarAgendaAtendimento(Agenda filtroAge, Paciente filtroPac) throws SystemException;

    public List<Agenda> listarAgendaMed(String data, int idMed) throws SystemException;
}
