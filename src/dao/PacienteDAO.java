package dao;

import dominio.Paciente;
import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.util.List;

public interface PacienteDAO {

    public void inserir(Paciente pac) throws DuplicadoException, SystemException;

    public void atualizar(Paciente pac) throws DuplicadoException, SystemException;

    public void excluir(Paciente pac) throws SystemException;

    public List<Paciente> listar() throws SystemException;

    public List<Paciente> pesquisar(Paciente idPac) throws SystemException;

    public List<Paciente> pesquiserFiltro(Paciente filtro) throws SystemException;

    public List<Paciente> pesquiserFiltroAvancado(Paciente filtro) throws SystemException;
}
