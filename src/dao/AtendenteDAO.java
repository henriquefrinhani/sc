package dao;

import dominio.Atendente;
import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.util.List;

public interface AtendenteDAO {

    public void inserirAtendente(Atendente ate) throws SystemException, DuplicadoException;

    public void atualizarAtendente(Atendente ate) throws SystemException, DuplicadoException;

    public void excluirAtendente(Atendente ate) throws SystemException;

    public Atendente pesquisarMatricula(int matricula) throws SystemException;

    public List<Atendente> pesquisarAtendente(Atendente filtro) throws SystemException;
}
