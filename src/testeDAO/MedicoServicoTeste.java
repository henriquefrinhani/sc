package testeDAO;

import dominio.Medico;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import servico.MedicoServico;

public class MedicoServicoTeste {

    public static void main(String[] args) throws NegocioException, SystemException {
        Medico m = new Medico();

       // m.setMatricula(24);
        m.setNome("joao");
        m.setCpf("04081942194");
        m.setRg(29781);
        m.setCrm("CR4445");
        m.setSexo("M");
        m.setStatus(true);
        m.setTelefone("61 9574-6542");
        m.setIdEndereco(5);
        m.setIdEspecialidade(1);

        try {
            //MedicoServico.atualizarMedico(m);
            MedicoServico.inserirMedico(m);
            // MedicoServico.excluir(m);
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
        }

        
        /*
         try {
           List<Medico> lista = MedicoServico.listarMedicos();
        // List<Medico> lista = MedicoServico.pesuisarMedico(null);

         for (Medico med: lista) {
         System.out.println("Matricula: "+med.getMatricula());
         System.out.println("Nome: "+med.getNome());
         System.out.println("CPF: "+med.getCpf());
         System.out.println("RG: "+med.getRg());
         System.out.println("CRM: "+med.getCrm());
         System.out.println("Telefone: "+med.getTelefone());
         System.out.println("Status: "+med.isStatus());
         System.out.println("Endereço: "+med.getIdEndereco());
         System.out.println("Especialidade: "+med.getIdEspecialidade());
         System.out.println("--------------------------------------");
         }
         } catch (NegocioException e) {
         System.out.println("Erro");
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
