package servico;

import dao.EnderecoDAO;
import dao.EnderecoDAOImpl;
import dao.PacienteDAO;
import dao.PacienteDAOImpl;
import dominio.Endereco;
import dominio.Paciente;
import excecoes.DuplicadoException;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import mensagem.Mensagem;
import util.CpfUtil;
import util.ObjetoUtil;

public class PacienteServico extends ServicoAbstrato {

    public static void inserirPaciente(Paciente paciente) throws DuplicadoException, SystemException {
        validarPaciente(paciente);

        Connection conn = obterConnection();
        PacienteDAO pd = new PacienteDAOImpl(conn);

        if (ObjetoUtil.isVazio(paciente.getIdPac())) {
            pd.inserir(paciente);
        } else {
            pd.atualizar(paciente);
        }
        close(conn);
    }

    public static void inserirPacienteEndereco(Paciente paciente, Endereco endereco) throws DuplicadoException, SystemException {
        validarPacienteEndereco(endereco, paciente);
        Integer idEnd = null;

        Connection conn = obterConnection();
        EnderecoDAO ed = new EnderecoDAOImpl(conn);
        PacienteDAO pd = new PacienteDAOImpl(conn);

        if (ObjetoUtil.isVazio(endereco.getIdEndereco())) {
            ed.inserirEnd(endereco);
            idEnd = endereco.getIdEndereco();
        } else {
            ed.atualizarEnd(endereco);
            idEnd = endereco.getIdEndereco();
        }

        if (ObjetoUtil.isVazio(paciente.getIdPac())) {
            paciente.setIdEndereco(idEnd);
            pd.inserir(paciente);
        } else {
            paciente.setIdEndereco(idEnd);
            pd.atualizar(paciente);
        }
        close(conn);
    }

    /* public static void atualizarPaciente(Paciente paciente) throws DuplicadoException, SystemException {
     validarPaciente(paciente);
     Connection conn = obterConnection();
     PacienteDAO pd = new PacienteDAOImpl(conn);
     pd.atualizar(paciente);
     close(conn);
     }*/
    public static void excluirPaciente(Paciente pac) throws SystemException {
        if (ObjetoUtil.isVazio(pac.getIdPac())) {
            return;
        }

        Connection conn = obterConnection();
        PacienteDAO pd = new PacienteDAOImpl(conn);
        pd.excluir(pac);
        close(conn);
    }

    public static List<Paciente> pesquisarPaciente(Paciente filtro) throws SystemException {
        if (filtro == null) {
            filtro = new Paciente();
        }

        Connection conn = obterConnection();
        PacienteDAO pd = new PacienteDAOImpl(conn);
        List<Paciente> pesquisa = pd.pesquiserFiltro(filtro);
        close(conn);
        return pesquisa;
    }

    public static List<Paciente> pesquisarPacienteAvancado(Paciente filtro) throws SystemException {

        Connection conn = obterConnection();
        PacienteDAO pd = new PacienteDAOImpl(conn);
        List<Paciente> pesquisa = pd.pesquiserFiltroAvancado(filtro);
        close(conn);
        return pesquisa;
    }

    private static void validarPaciente(Paciente pac) throws NegocioException {
        List<Mensagem> mensagens = new ArrayList<Mensagem>();

        if (ObjetoUtil.isVazio(pac.getNome())) {
            mensagens.add(criarMsgObrig("Nome"));
        }

        if (ObjetoUtil.isVazio(pac.getCpf())) {
            mensagens.add(criarMsgObrig("CPF"));
        } else if (!CpfUtil.validar(pac.getCpf())) {
            mensagens.add(criarMsgCpfInvalido(pac.getCpf()));
        }
        /*
         if (ObjetoUtil.isNulo(pac.getRg())) {
         mensagens.add(criarMsgObrig("Registro Geral"));
         }*/

        if (ObjetoUtil.isVazio(pac.getSexo())) {
            mensagens.add(criarMsgObrig("Sexo"));
        }

        if (ObjetoUtil.isVazio(pac.getDataNac())) {
            mensagens.add(criarMsgObrig("Data de Nascimento"));
        }

        if (ObjetoUtil.isVazio(pac.getEstadoCivil())) {
            mensagens.add(criarMsgObrig("Estado Civil"));
        }

        if (ObjetoUtil.isVazio(pac.getTelefone())) {
            mensagens.add(criarMsgObrig("Telefone"));
        }

        /*if (ObjetoUtil.isVazio(pac.getIdEndereco())) {
         mensagens.add(criarMsgObrig("Endereço"));
         }*/
        if (mensagens.size() > 0) {
            throw new NegocioException(mensagens);
        }
    }

    private static void validarPacientePesquisa(Paciente pac) throws NegocioException {
        List<Mensagem> mensagens = new ArrayList<Mensagem>();

        if (ObjetoUtil.isVazio(pac.getNome())) {
            mensagens.add(criarMsgObrig("Nome"));
        }

        if (ObjetoUtil.isVazio(pac.getCpf())) {
            mensagens.add(criarMsgObrig("CPF"));
        } else if (!CpfUtil.validar(pac.getCpf())) {
            mensagens.add(criarMsgCpfInvalido(pac.getCpf()));
        }

        if (ObjetoUtil.isVazio(pac.getDataNac())) {
            mensagens.add(criarMsgObrig("Data de Nascimento"));
        }

        if (mensagens.size() > 0) {
            throw new NegocioException(mensagens);
        }
    }
    
    
    private static void validarPacienteEndereco(Endereco end, Paciente pac ) throws NegocioException {
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
        
        if (ObjetoUtil.isVazio(pac.getNome())) {
            mensagens.add(criarMsgObrig("Nome"));
        }

        if (ObjetoUtil.isVazio(pac.getCpf())) {
            mensagens.add(criarMsgObrig("CPF"));
        } else if (!CpfUtil.validar(pac.getCpf())) {
            mensagens.add(criarMsgCpfInvalido(pac.getCpf()));
        }
        
        if (ObjetoUtil.isVazio(pac.getSexo())) {
            mensagens.add(criarMsgObrig("Sexo"));
        }

        if (ObjetoUtil.isVazio(pac.getDataNac())) {
            mensagens.add(criarMsgObrig("Data de Nascimento"));
        }

        if (ObjetoUtil.isVazio(pac.getEstadoCivil())) {
            mensagens.add(criarMsgObrig("Estado Civil"));
        }

        if (ObjetoUtil.isVazio(pac.getTelefone())) {
            mensagens.add(criarMsgObrig("Telefone"));
        }

        
        if (mensagens.size() > 0) {
            throw new NegocioException(mensagens);
        }
    }
}
