package config;

import java.util.ResourceBundle;

public final class Config {
    
    private static ResourceBundle bundle;
    
    private Config(){
    }
    
    static{
        bundle = ResourceBundle.getBundle("properties.Config");
    }
    
    public static String getString(String chave){
        try{
            return bundle.getString(chave);
        }catch(Exception e){
            throw new IllegalStateException("Erro ao ler a propriedade chave");
        }
    }
    
    public static boolean contemChave(String chave){
        if (chave == null || chave.trim().length() ==0){
            return false;
        }
        
        return bundle.containsKey(chave);
    }
    
    
}
