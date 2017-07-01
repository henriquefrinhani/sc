package testeDAO;

import dominio.Administrador;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import servico.AdministradorServico;

public class AdministradorSerivicoTeste {

    public static void main(String[] args) throws SystemException, NegocioException {
        Administrador adm = new Administrador();

        adm.setMatricula(3);
        adm.setNome("Pedro");
        adm.setCpf("04081942196");
        adm.setRg(984553);
        adm.setSexo("M");
        adm.setTelefone(" 11 99562-3492");
        adm.setIdLogin(1);
        adm.setIdEndereco(1);

        
         try {
         AdministradorServico.inserirAdmin(adm);
         //AdministradorServico.atualizarAdmin(adm);
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
        
       /*try {
            //List<Administrador> lista = AdministradorServico.listarAdmin();
            List<Administrador> lista = AdministradorServico.pesquisarAdmin(adm);

            for (Administrador a : lista) {
                System.out.println("Matricula: " + a.getMatricula());
                System.out.println("nome: " + a.getNome());
                System.out.println("Telefone: " + a.getTelefone());
                System.out.println("CPF: " + a.getCpf());
                System.out.println("RG: " + a.getRg());
                System.out.println("Sexo: " + a.getSexo());
                System.out.println("Id Endereço: " + a.getIdEndereco());
                System.out.println("Id Login: "+a.getIdLogin());
                System.out.println("-----------------------");
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
            if (e.getMensagens().size() > 0) {
                List<String> l = new ArrayList<>();

                for (int i = 0; i < e.getMensagens().size(); i++) {
                    l.add(e.getMensagens().get(i).getMensagem());
                }
                JOptionPane.showMessageDialog(null, l.toArray());
            } else {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }*/
    }
}

