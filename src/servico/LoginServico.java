package servico;

import dao.LoginDAO;
import dao.LoginDAOImpl;
import dominio.Atendente;
import dominio.Login;
import excecoes.DuplicadoException;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import mensagem.Mensagem;
import static servico.ServicoAbstrato.obterConnection;
import util.ObjetoUtil;

public class LoginServico extends ServicoAbstrato {

    public static void inserirLogin(Login log) throws SystemException, DuplicadoException {
        validarLogin(log);

        Connection conn = obterConnection();
        LoginDAO ld = new LoginDAOImpl(conn);
        ld.inserirLogin(log);
        close(conn);
    }

    public static int altenticarLogin(Login log) throws SystemException, NegocioException{
        Connection conn = obterConnection();
        LoginDAO ld = new LoginDAOImpl(conn);
        int i =ld.altenticarLogin(log);
        close(conn);
        
        return i;
    }

    public static boolean altenticarSenha(Login log) throws SystemException {
        Connection conn = obterConnection();
        LoginDAO ld = new LoginDAOImpl(conn);
        boolean b =ld.altenticarSenha(log);
        close(conn);
        return b;
    }

    public static void alterarSenha(Login log) throws SystemException {
        altenticarLogin(log);

        Connection conn = obterConnection();
        LoginDAO ld = new LoginDAOImpl(conn);
        ld.alterarSenha(log);
        close(conn);
    }

    public static boolean isLogin(Login log) throws SystemException {
        Connection conn = obterConnection();
        LoginDAO ld = new LoginDAOImpl(conn);
        boolean b = ld.isLogin(log);
        close(conn);
        return b;
    }
    
      public static Atendente listarAcessoAtendente(String usuario) throws SystemException, NegocioException {
        Connection conn = obterConnection();
        LoginDAO ld = new LoginDAOImpl(conn);
        Atendente ate = ld.acessoAtendente(usuario);
        close(conn);
         return ate;
    }

    private static void validarLogin(Login log) throws NegocioException {
        List<Mensagem> mensagens = new ArrayList<Mensagem>();

        if (!ObjetoUtil.isVazio(log.getUsuario())) {
            mensagens.add(criarMsgObrig("Usuário"));
        }

        if (!ObjetoUtil.isVazio(log.getSenha())) {
            mensagens.add(criarMsgObrig("Senha"));
        }

        if (!ObjetoUtil.isVazio(log.getPerfil())) {
            mensagens.add(criarMsgObrig("Perfil"));
        }

        if (mensagens.size() > 0) {
            throw new NegocioException(mensagens);
        }
    }

    private static void validarNovaSenha(Login log) throws NegocioException {
        List<Mensagem> mensagens = new ArrayList<Mensagem>();

        if (!ObjetoUtil.isVazio(log.getNovaSenha())) {
            mensagens.add(criarMsgObrig("Nova Senha"));
        }

        if (mensagens.size() > 0) {
            throw new NegocioException(mensagens);
        }
    }
}
