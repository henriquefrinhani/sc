package excecoes;

import java.util.List;
import mensagem.Mensagem;


public class DuplicadoException extends SystemException{
    private static final long serialVersionUID = 1L;
    
    public DuplicadoException(String erro){
        super(erro);
    }
    
    public DuplicadoException(List<Mensagem> mensagens) {
        super(mensagens);
    }
    
}
