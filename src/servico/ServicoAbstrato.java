package servico;

import conexao.ConexaoException;
import conexao.FabricaConexao;
import dao.DAOAbstrato;
import excecoes.SystemException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mensagem.Mensagem;
import mensagem.Mensagens;
import mensagem.TipoMensagem;
import util.ObjetoUtil;

public abstract class ServicoAbstrato {

    protected static Connection obterConnection() throws SystemException {// Analizar 
        try {
            Connection conn = FabricaConexao.getConnectionMysql();
            return conn;
        } catch (ConexaoException e) {
            if (!ObjetoUtil.isVazio(e.getMessage())) {
                List<Mensagem> erros = new ArrayList<Mensagem>();
                erros.add(new Mensagem(TipoMensagem.ERRO, e.getMessage(), false));
                throw new SystemException(erros);
           } else {
                List<Mensagem> erros = new ArrayList<Mensagem>();
                String msg = Mensagens.getString("error_sistema", "");
                erros.add(new Mensagem(TipoMensagem.ERRO, msg, false));
                throw new SystemException(erros);
            }
        }
    }

    protected static void close(Connection conn) {
        DAOAbstrato.fecharRecursos(conn, null, null);
    }

    protected static Mensagem criarMsgObrig(String campo) {
        String msg = Mensagens.getString("error.campo.obrigatorio", campo);
        return new Mensagem(TipoMensagem.ERRO, msg, false);
    }

    protected static Mensagem criarMsgCpfInvalido(String campo) {
        String msg = Mensagens.getString("error.cpf.invalido", campo);
        return new Mensagem(TipoMensagem.ERRO, msg, false);
    }
    
    protected static Mensagem criarMsgErroSistema(){
         String msg = Mensagens.getString("error_sistema", "");
          return new Mensagem(TipoMensagem.ERRO, msg, false);
               
    }
}
