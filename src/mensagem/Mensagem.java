package mensagem;

public class Mensagem {
    private final TipoMensagem tipo;
    private final String mensagem;
    private boolean isChave;
    
    public Mensagem(TipoMensagem tipo, String mensagem){
        this(tipo, mensagem, true);
    }
    
     public Mensagem(TipoMensagem tipo, String mensagem, boolean isChave){
        if(tipo == null){
            throw new IllegalArgumentException("Tipo da mensagem null.");
        }
        
        if(mensagem == null){
            throw new IllegalArgumentException("Mensagem null.");
        }
        
        this.tipo = tipo;
        this.mensagem = mensagem;
        this.isChave = isChave;
    }
     
     public TipoMensagem getTipo (){
         return tipo;
     }
     
     public String getMensagem(){
        return mensagem;
     }
     
     public Boolean getIsChave(){
         return isChave;
     } 
}
