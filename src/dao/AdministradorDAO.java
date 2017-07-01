package dao;

import dominio.Administrador;
import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.util.List;

public interface AdministradorDAO {

    public void inserirAdmin(Administrador adm) throws SystemException, DuplicadoException;

    public void atualizarAdmin(Administrador adm) throws SystemException, DuplicadoException;

    public void excluirAdmin(Administrador adm) throws SystemException;

    public List<Administrador> listaAdmin() throws SystemException;

    public List<Administrador> pesquisa(Administrador filtro) throws SystemException;
}
