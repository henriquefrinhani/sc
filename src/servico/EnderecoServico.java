package servico;

import dao.EnderecoDAO;
import dao.EnderecoDAOImpl;
import dominio.Endereco;
import excecoes.DuplicadoException;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import mensagem.Mensagem;
import static servico.ServicoAbstrato.obterConnection;
import util.ObjetoUtil;

public class EnderecoServico extends ServicoAbstrato {

    public static void inserirEndereco(Endereco end) throws DuplicadoException, SystemException {
        validarEndereco(end);

        Connection conn = obterConnection();
        EnderecoDAO ed = new EnderecoDAOImpl(conn);

        if (ObjetoUtil.isVazio(end.getIdEndereco())) {
            ed.inserirEnd(end);
            System.out.println("Inserir");
        } else {
            System.out.println("Atualizar");
            ed.atualizarEnd(end);
        }
        close(conn);
    }

    public static Endereco buscarEndereco(int idEndereco) throws SystemException {

        Connection conn = obterConnection();
        EnderecoDAO ed = new EnderecoDAOImpl(conn);
        Endereco end = ed.buscarEndereco(idEndereco);
        close(conn);
        return end;
    }

    private static void validarEndereco(Endereco end) throws NegocioException {
        List<Mensagem> mensagens = new ArrayList<Mensagem>();

        if (ObjetoUtil.isVazio(end.getCep())) {
            mensagens.add(criarMsgObrig("CEP"));
        }

        if (ObjetoUtil.isVazio(end.getUf())) {
            mensagens.add(criarMsgObrig("UF"));
        }

        if (ObjetoUtil.isVazio(end.getRua())) {
            mensagens.add(criarMsgObrig("Rua"));
        }

        if (ObjetoUtil.isVazio(end.getCidade())) {
            mensagens.add(criarMsgObrig("Cidade"));
        }

        if (mensagens.size() > 0) {
            throw new NegocioException(mensagens);
        }
    }

}
