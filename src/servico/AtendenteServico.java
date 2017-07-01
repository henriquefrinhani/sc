package servico;

import dao.AtendenteDAO;
import dao.AtendenteDAOImpl;
import dao.EnderecoDAO;
import dao.EnderecoDAOImpl;
import dao.LoginDAO;
import dao.LoginDAOImpl;
import dominio.Atendente;
import dominio.Endereco;
import dominio.Login;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import mensagem.Mensagem;
import util.CpfUtil;
import util.ObjetoUtil;

public class AtendenteServico extends ServicoAbstrato {
    
    public static void inserirAtendente(Atendente ate, Endereco endereco, Login log) throws SystemException {
        validarAtendente(ate, endereco);
        
        Integer idEnd = null;
        Integer matricula = null;
        
        Connection conn = obterConnection();
        EnderecoDAO ed = new EnderecoDAOImpl(conn);
        AtendenteDAO ad = new AtendenteDAOImpl(conn);
        LoginDAO ld = new LoginDAOImpl(conn);
        
        if (ObjetoUtil.isVazio(endereco.getIdEndereco())) {
            ed.inserirEnd(endereco);
            idEnd = endereco.getIdEndereco();
        } else {
            ed.atualizarEnd(endereco);
            idEnd = endereco.getIdEndereco();
        }
        
        if (ObjetoUtil.isVazio(ate.getMatricula())) {
            ate.setIdEndereco(idEnd);
            ad.inserirAtendente(ate);
            matricula = ate.getMatricula();
            
        } else {
            ate.setIdEndereco(idEnd);
            ad.atualizarAtendente(ate);
            matricula = ate.getMatricula();
        }
        
        System.out.println("idLogn: "+log.getIdLogin());
        if (ObjetoUtil.isVazio(log.getIdLogin())) {
            log.setIdFuncionario(matricula);
            ld.inserirLogin(log);
        } else {
            
        }
        
        close(conn);
    }
    
    public static void excluirAtendente(Atendente ate) throws SystemException {
        Connection conn = obterConnection();
        AtendenteDAO pd = new AtendenteDAOImpl(conn);
        pd.excluirAtendente(ate);
        close(conn);
    }
    
    public static Atendente pesquisarAtenMatricula(int matricula) throws SystemException {
        Connection conn = obterConnection();
        AtendenteDAO pd = new AtendenteDAOImpl(conn);
        Atendente ate = pd.pesquisarMatricula(matricula);
        close(conn);
        return ate;
    }
    
    public static List<Atendente> pesquisarAtendente(Atendente filtro) throws SystemException {
        if (filtro == null) {
            filtro = new Atendente();
        }
        Connection conn = obterConnection();
        AtendenteDAO pd = new AtendenteDAOImpl(conn);
        List<Atendente> lista = pd.pesquisarAtendente(filtro);
        close(conn);
        return lista;
    }
    
    private static void validarAtendente(Atendente ate, Endereco endereco) throws NegocioException {
        List<Mensagem> mensagens = new ArrayList<Mensagem>();
        
        if (ObjetoUtil.isVazio(ate.getNome())) {
            mensagens.add(criarMsgObrig("nome"));
        }
        
        if (ObjetoUtil.isVazio(ate.getCpf())) {
            mensagens.add(criarMsgObrig("CPF"));
        } else if (!CpfUtil.validar(ate.getCpf())) {
            mensagens.add(criarMsgCpfInvalido(ate.getCpf()));
        }
        
        if (ObjetoUtil.isVazio(ate.getRg())) {
            mensagens.add(criarMsgObrig("RG"));
        }
        
        if (ObjetoUtil.isVazio(ate.getSexo())) {
            mensagens.add(criarMsgObrig("Sexo"));
        }
        
        if (ObjetoUtil.isVazio(ate.getTelefone())) {
            mensagens.add(criarMsgObrig("Telefone"));
        }
        
        if (ObjetoUtil.isVazio(ate.getStatus())) {
            mensagens.add(criarMsgObrig("Status"));
        }
        
        if (ObjetoUtil.isVazio(endereco.getCep())) {
            mensagens.add(criarMsgObrig("CEP"));
        }

        /* if (ObjetoUtil.isVazio(endereco.getBairro())) {
         mensagens.add(criarMsgObrig("Bairro"));
         }*/
        if (ObjetoUtil.isVazio(endereco.getCidade())) {
            mensagens.add(criarMsgObrig("Cidade"));
        }
        
        if (ObjetoUtil.isVazio(endereco.getRua())) {
            mensagens.add(criarMsgObrig("Rua"));
        }
        
        if (ObjetoUtil.isVazio(endereco.getUf())) {
            mensagens.add(criarMsgObrig("UF"));
        }
        
        if (mensagens.size() > 0) {
            throw new NegocioException(mensagens);
            
        }
    }
}
