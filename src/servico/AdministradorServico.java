package servico;

import dao.AdministradorDAO;
import dao.AdministradorDAOImpl;
import dominio.Administrador;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import mensagem.Mensagem;
import util.CpfUtil;
import util.ObjetoUtil;

public class AdministradorServico extends ServicoAbstrato {
    
    public static void inserirAdmin(Administrador adm) throws SystemException {
        validarAdmin(adm);
        Connection conn = obterConnection();
        AdministradorDAO ad = new AdministradorDAOImpl(conn);
        ad.inserirAdmin(adm);
    }
    
    public static void atualizarAdmin(Administrador adm) throws SystemException {
        validarAdmin(adm);
        Connection conn = obterConnection();
        AdministradorDAO ad = new AdministradorDAOImpl(conn);
        ad.atualizarAdmin(adm);
    }
    
    public static void excluirAdmin(Administrador adm) throws SystemException {
        Connection conn = obterConnection();
        AdministradorDAO ad = new AdministradorDAOImpl(conn);
        ad.equals(adm);
    }
    
    public static List<Administrador> listarAdmin() throws SystemException {
        Connection conn = obterConnection();
        AdministradorDAO ad = new AdministradorDAOImpl(conn);
        List<Administrador> lista = ad.listaAdmin();
        return lista;
    }
    
    public static List<Administrador> pesquisarAdmin(Administrador filtro) throws SystemException {
        if(filtro == null){
          filtro = new Administrador();
        }
        Connection conn = obterConnection();
        AdministradorDAO ad = new AdministradorDAOImpl(conn);
        List<Administrador> lista = ad.pesquisa(filtro);
        return lista;
    }
    
    private static void validarAdmin(Administrador adm) throws NegocioException {
        List<Mensagem> mensagens = new ArrayList<Mensagem>();
        
        if (ObjetoUtil.isVazio(adm.getNome())) {
            mensagens.add(criarMsgObrig("Nome"));
        }
        
        if (ObjetoUtil.isVazio(adm.getCpf())) {
            mensagens.add(criarMsgObrig("CPF"));
        } else if (!CpfUtil.validar(adm.getCpf())) {
            mensagens.add(criarMsgCpfInvalido(adm.getCpf()));
        }
        
        if (ObjetoUtil.isVazio(adm.getRg())) {
            mensagens.add(criarMsgObrig("Registro Geral"));
        }
        if (ObjetoUtil.isVazio(adm.getSexo())) {
            mensagens.add(criarMsgObrig("Sexo"));
        }
        
        if (ObjetoUtil.isVazio(adm.getTelefone())) {
            mensagens.add(criarMsgObrig("Telefone"));
        }
        
        if (mensagens.size() > 0) {
            throw new NegocioException(mensagens);
        }
    }
}
