package testeDAO;

import conexao.ConexaoException;
import conexao.FabricaConexao;
import dao.PacienteDAO;
import dao.PacienteDAOImpl;
import servico.PacienteServico;
import dominio.Paciente;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PacienteServicoTeste {

    public static void main(String[] args) throws SystemException, NegocioException {
        Paciente p = new Paciente();

        //  p.setIdPac(1015);
    //    p.setNome("MARIA TESTE");
        //  p.setNomeSocial("Toni");
     //   p.setTelefone("61 4657-2454");
      //  p.setCelular("61 9141-2541");
        // p.setEmail("antonio@email.com");
        p.setCpf("04081942196");
       // p.setRg(2978);
       // p.setDataNac("1989-07-27");
       // p.setSexo("M");
      // p.setIdade(24);
       // p.setEstadoCivil("Solteiro");
      // p.setIdEndereco();

        
        /*
        //Inserir Paciente
        try {

            PacienteServico.inserirPaciente(p);
            //PacienteServico.atualizarPaciente(p);
            //Connection conn = FabricaConexao.getConnectionMysql();

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

            List<String> l = new ArrayList<>();
            if (e.getMensagens().size() > 0) {
                for (int i = 0; i < e.getMensagens().size(); i++) {
                    l.add(e.getMensagens().get(i).getMensagem());
                }
                JOptionPane.showMessageDialog(null, l.toArray());
            } else {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
        /*catch (IllegalStateException e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
         } catch (IllegalArgumentException e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
         }*/
        //excluir
        // PacienteServico.excluirPaciente(p);
        
        try {
         List<Paciente> lista = PacienteServico.pesquisarPaciente(p);

         for (Paciente pac : lista) {
         System.out.println("id: " + pac.getIdPac());
         System.out.println("nome: " + pac.getNome());
         System.out.println("Nome socical: " + pac.getNomeSocial());
         System.out.println("Telefone: " + pac.getTelefone());
         System.out.println("CPF: " + pac.getCpf());
         System.out.println("RG: " + pac.getRg());
         System.out.println("Sexo: " + pac.getSexo());
         System.out.println("Idade: " + pac.getIdade());
         System.out.println("Data Nascimento: " + pac.getDataNac());
         System.out.println("Estado Civil: " + pac.getEstadoCivil());
         System.out.println("Id Endereço: " + pac.getIdEndereco());
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
         }

    }
}
