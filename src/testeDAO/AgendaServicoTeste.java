package testeDAO;

import dominio.Agenda;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import servico.AgendaServico;

public class AgendaServicoTeste {

    public static void main(String[] args) throws SystemException {

        try {
            List<Agenda> listaAgenda = AgendaServico.listaAgenda("2016-03-01", 6);

            for (Agenda a : listaAgenda) {
                System.out.println("Código: " + a.getIdConsulta());
                System.out.println("Data: " + a.getData());
                System.out.println("Paciente: " + a.getIdPac() + " - " + a.getNomPac());
                System.out.println("Médico: " + a.getMatMed() + " - " + a.getNomMed());
                System.out.println("Index: " + a.getIndice());
                System.out.println("-------------");
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
