package servico;

import dao.MedicoDAO;
import dao.MedicoDAOImpl;
import dominio.Medico;
import excecoes.DuplicadoException;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import mensagem.Mensagem;
import util.CpfUtil;
import util.ObjetoUtil;

public class MedicoServico extends ServicoAbstrato {

    public static void inserirMedico(Medico med) throws SystemException, DuplicadoException {
       // validarMedico(med);

        Connection conn = obterConnection();
        MedicoDAO md = new MedicoDAOImpl(conn);
        md.inserir(med);
        close(conn);
    }

    public static void atualizarMedico(Medico med) throws SystemException, DuplicadoException {
        validarMedico(med);

        Connection conn = obterConnection();
        MedicoDAO md = new MedicoDAOImpl(conn);
        md.atualizar(med);
        close(conn);
    }

    public static void excluir(Medico med) throws SystemException {
        Connection conn = obterConnection();
        MedicoDAO md = new MedicoDAOImpl(conn);
        md.excliur(med);
        close(conn);
    }

    public static List<Medico> pesuisarMedico(Medico filtro) throws SystemException {
        if (filtro == null) {
            filtro = new Medico();
        }

        Connection conn = obterConnection();
        MedicoDAO pd = new MedicoDAOImpl(conn);
        List<Medico> res = pd.pesquisar(filtro);
        close(conn);
        return res;
    }

    public static List<Medico> listarMedicos() throws SystemException {
        Connection conn = obterConnection();
        MedicoDAO pd = new MedicoDAOImpl(conn);
        List<Medico> res = pd.lista();
        close(conn);
        return res;
    }

    private static void validarMedico(Medico med) throws NegocioException {
        List<Mensagem> mensagens = new ArrayList<>();

        if (ObjetoUtil.isVazio(med.getNome())) {
            mensagens.add(criarMsgObrig("Nome"));
        }

        if (ObjetoUtil.isVazio(med.getCpf())) {
            mensagens.add(criarMsgObrig("CPF"));
        } else if (!CpfUtil.validar(med.getCpf())) {
            mensagens.add(criarMsgCpfInvalido(med.getCpf()));
        }

        if (ObjetoUtil.isVazio(med.getRg())) {
            mensagens.add(criarMsgObrig("Registro Geral"));
        }

        if (ObjetoUtil.isVazio(med.getCrm())) {
            mensagens.add(criarMsgObrig("CRM"));
        }
        if (ObjetoUtil.isVazio(med.getTelefone())) {
            mensagens.add(criarMsgObrig("Telefone"));
        }
        if (ObjetoUtil.isVazio(med.getSexo())) {
            mensagens.add(criarMsgObrig("sexo"));
        }

        if (mensagens.size() > 0) {
            throw new NegocioException(mensagens);
        }
    }
}
