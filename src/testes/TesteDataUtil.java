package testes;

import excecoes.NegocioException;
import excecoes.SystemException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
//import junit.framework.TestCase;
import util.DataUtil;

public class TesteDataUtil {

    public static void main(String[] args) throws NegocioException {
       //usar a nova api de data
    	
    	/* Date data = new Date();
    
         data.getTime();
          
         System.out.println(data);
     
         System.out.println("Data Formadata "+DataUtil.formatoData(data));*/
        try {
            System.out.println(DataUtil.parseDate("21.04.1991", "dd.MM.yyyg"));
        } catch (NegocioException ex) {
            if (ex.getMensagens().size() > 0) {
                List<String> lista = new ArrayList<>();

                for (int i = 0; i < ex.getMensagens().size(); i++) {
                    lista.add(ex.getMensagens().get(i).getMensagem());
                }
                JOptionPane.showMessageDialog(null, lista.toArray());
            } else {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } catch (SystemException ex) {
            if (ex.getMensagens().size() > 0) {
                List<String> lista = new ArrayList<>();

                for (int i = 0; i < ex.getMensagens().size(); i++) {
                    lista.add(ex.getMensagens().get(i).getMensagem());
                }
                JOptionPane.showMessageDialog(null, lista.toArray());
            } else {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }

}
