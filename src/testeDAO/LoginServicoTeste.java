package testeDAO;

import dao.LoginDAO;
import dominio.Atendente;
import dominio.Login;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import servico.LoginServico;

public class LoginServicoTeste {

    public static void main(String[] args) {

        // Login l = new Login();
        Atendente a = new Atendente();

        String usuario = "atendente";

        try {
            a = LoginServico.listarAcessoAtendente(usuario);
         
                System.out.println(a.getNome());
                System.out.println(a.getMatricula());
            
        } catch (NegocioException e) {
            if (e.getMensagens().size() > 0) {
                List<String> l = new ArrayList<>();

                for (int i = 0; i < e.getMensagens().size(); i++) {
                    l.add(e.getMensagens().get(i).getMensagem());
                }
                JOptionPane.showMessageDialog(null, l.toArray());
            } else {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } catch (SystemException e) {
            if (e.getMensagens().size() > 0) {
                List<String> l = new ArrayList<>();

                for (int i = 0; i < e.getMensagens().size(); i++) {
                    l.add(e.getMensagens().get(i).getMensagem());
                }
                JOptionPane.showMessageDialog(null, l.toArray());
            } else {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
}
