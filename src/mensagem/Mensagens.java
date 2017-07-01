package mensagem;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public final class Mensagens {
    
    private static ResourceBundle bundle;
    
    private Mensagens(){
    }
    
    static{
        bundle = ResourceBundle.getBundle("properties.mensagens");
    }
    
    public static String getString(String chave, Object...argumentos){
        try{
            if(argumentos.length ==0){
                return bundle.getString(chave);
            } else{
                String string = bundle.getString(chave);
                return MessageFormat.format(string, argumentos);
            }
            
        }catch(Exception e){
            throw new IllegalStateException("Erro ao ler a propriedade: "+chave);
        }
    }
    
    public static boolean contemChave(String chave){
        if(chave == null || chave.trim().length() == 0){
            return false;
        }
        
        return bundle.containsKey(chave);
    }
}
