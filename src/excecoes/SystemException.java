package excecoes;

import java.util.ArrayList;
import java.util.List;
import mensagem.Mensagem;


public class SystemException extends Exception{
    private static final long serialVersionUID = 1L;
    private final List<Mensagem> mensagens;
    
    public SystemException(String erro){
        super(erro);
        mensagens = new ArrayList<Mensagem>();
    }
    
    public SystemException(List<Mensagem> mensagens){
        this.mensagens = mensagens;
    }
   

    public List<Mensagem> getMensagens(){
        return mensagens;
    }
}
