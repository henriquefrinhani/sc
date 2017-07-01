package testeDAO;

import dominio.Atendente;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import servico.AtendenteServico;

public class AtendenteServicoTeste {

    public static void main(String[] args) throws SystemException, NegocioException {
        Atendente a = new Atendente();

       // a.setMatricula(5);
        a.setNome("Henrique de oliveira Frinhani");
        a.setCpf("04081942196");
        a.setRg(226545);
        a.setSexo("M");
        a.setTelefone("61 9965-5432");
        a.setStatus(true);
        a.setIdEndereco(4);

        /*
        try {
          AtendenteServico.inserirAtendente(a);
         //AtendenteServico.excluirAtendente(a);
           // AtendenteServico.atualizarAtendente(a);
          //  int matricula = 1001;
          /*  Atendente at = AtendenteServico.pesquisarAtenMatricula(a);
            System.out.println("Usuário: "+at.getNome());
            System.out.println("Matrícula: "+at.getMatricula());*/
             
        /* } catch (NegocioException e) {
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
         JOptionPane.showMessageDialog(null, e.getMessage());
         }
        */
        /*
        try {
            List<Atendente> lista = AtendenteServico.pesquisarAtendente(a);

            for (Atendente ate : lista) {
                System.out.println("Matricula: "+ate.getMatricula());
                System.out.println("Nome: "+ate.getNome());
                System.out.println("CPF: "+ate.getCpf());
                System.out.println("RG: "+ate.getRg());
                System.out.println("Sexo: "+ate.getSexo());
                System.out.println("Telefone: "+ate.getTelefone());
                System.out.println("idEndelo: "+ate.getIdEndereco());
                System.out.println("------------------------");
            }
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
            JOptionPane.showMessageDialog(null, e.getMessage());
        }*/

    }
}
