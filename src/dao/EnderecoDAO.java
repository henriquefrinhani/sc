package dao;

import dominio.Endereco;
import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.util.List;

public interface EnderecoDAO {

    public void inserirEnd(Endereco end) throws SystemException, DuplicadoException;

    public void atualizarEnd(Endereco end) throws SystemException, DuplicadoException;

    public void excluirEnd(Endereco end) throws SystemException;
    
    public Endereco buscarEndereco(int idEndereco) throws SystemException;

    public List<Endereco> pesquisa(Endereco filtro) throws SystemException;

}
