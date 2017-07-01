package excecoes;

import java.util.List;
import mensagem.Mensagem;


public class NegocioException extends SystemException{
    
    private static final long serialVersionUID = 1L;
    
    public NegocioException(String erro) {
        super(erro);
    }
    
    public NegocioException(List<Mensagem> mensagens){
        super(mensagens);
    }
    
    
}
