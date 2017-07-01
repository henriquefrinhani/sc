package dao;

import dominio.Medico;
import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.util.List;

public interface MedicoDAO {

    public void inserir(Medico med) throws SystemException, DuplicadoException;

    public void atualizar(Medico med) throws SystemException, DuplicadoException;

    public void excliur(Medico med) throws SystemException;

    public List<Medico> lista() throws SystemException;

    public List<Medico> pesquisar(Medico filtro) throws SystemException;

}
