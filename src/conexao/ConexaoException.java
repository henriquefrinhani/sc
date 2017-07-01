package conexao;

import excecoes.SystemException;


public class ConexaoException extends SystemException{
    private static final long serialVersionUID = 1L;
    
    public ConexaoException(String erro) {
        super(erro);
    } 
}
