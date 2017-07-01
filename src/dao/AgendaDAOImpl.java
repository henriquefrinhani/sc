package dao;

import dominio.Agenda;
import dominio.Paciente;
import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DataUtil;
import util.ObjetoUtil;

public class AgendaDAOImpl extends DAOAbstrato implements AgendaDAO {

    private static final String INSERIR = "INSERT INTO agenda ( data, indice, tipoConsulta, prioridade, confirmacao, "
            + " situacao, paciente_idPaciente, medico_matricula) "
            + " VALUES (?,?,?,?,?,?,?,?)";

    private static final String ALTERAR = "UPDATE agenda SET data=?, paciente_idPaciente=? medico_matrica=? indice=? ";
    
    private static final String ALTERAR_ATENDIMENTO = "UPDATE agenda SET prioridade=?, situacao=?, tipoConsulta=? WHERE idConsulta=? ";

    private static final String EXCLUIR = "DELETE FROM agenda WHERE idConsulta=? ";

    private static final String PESQUISAR = "select a.prioridade, a.indice, a.Paciente_idPaciente, p.nome, a.tipoConsulta, a.confirmacao, f.nome, a.situacao \n"
            + " from agenda as a, paciente as p, medico as m, funcionario as f \n"
            + " where paciente_idpaciente = idpaciente and medico_matricula = funcionario_matricula and matricula = funcionario_matricula";

    private static final String PESQUISAR_ATENDIMENTO = "SELECT idConsulta, Data, prioridade, situacao, tipoConsulta From agenda as a, paciente as p Where Paciente_idPaciente=idPaciente";

    private static final String PESQUISAR_AGENDA_MEDICA = "select a.prioridade, a.Paciente_idPaciente, p.nome, a.tipoConsulta, a.confirmacao, a.situacao, a.indice \n"
            + " from agenda as a, paciente as p, medico as m, funcionario as f \n"
            + " where paciente_idpaciente = idpaciente and medico_matricula = funcionario_matricula and matricula = funcionario_matricula \n"
            + " and data = ? and funcionario_matricula = ? ";

    public AgendaDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public void inserir(Agenda age) throws SystemException, DuplicadoException {
        try {
            psmt = conn.prepareStatement(INSERIR);
            psmt.setString(1, age.getData());
            psmt.setInt(2, age.getIndice());
            psmt.setString(3, age.getTipoConsulta());
            psmt.setString(4, age.getPrioridade());
            psmt.setBoolean(5, age.isConfirmacao());
            psmt.setString(6, age.getSituacao());
            psmt.setInt(7, age.getIdPac());
            psmt.setInt(8, age.getMatMed());

            int res = psmt.executeUpdate();

            if (res == 1) {
                rs = psmt.getGeneratedKeys();
                rs.next();
                age.setIdConsulta(res);
            }
        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao marcar a consulta.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
    }

    @Override
    public void alterar(Agenda age) throws SystemException, DuplicadoException {
        try {
            psmt = conn.prepareStatement(ALTERAR);
            psmt.setString(1, age.getData());
            psmt.setInt(2, age.getIdPac());
            psmt.setInt(3, age.getMatMed());
            psmt.setInt(4, age.getIndice());
            psmt.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao remarcar consulta.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }

    }
    
       @Override
    public void alterarAtendimento(Agenda age) throws SystemException, DuplicadoException {
        try {
            psmt = conn.prepareStatement(ALTERAR_ATENDIMENTO);
            psmt.setString(1, age.getPrioridade());
            psmt.setString(2, age.getSituacao());
            psmt.setString(3, age.getTipoConsulta());
            psmt.setInt(4, age.getIdConsulta());
            psmt.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao alterar.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }

    }


    @Override
    public void excluir(Agenda age) throws SystemException {
        try {
            psmt = conn.prepareStatement(EXCLUIR);
            psmt.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao cancelar consulta");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
    }

    @Override
    public List<Agenda> PesquisarAgenda(Agenda filtroAge, Paciente filtroPac) throws SystemException {
        List<Agenda> lista = new ArrayList<Agenda>();

        try {
            psmt = conn.prepareStatement(PESQUISAR + completarSql(filtroAge, filtroPac));

            ResultSet res = psmt.executeQuery();

            if (res != null) {
                while (res.next()) {
                    Agenda a = new Agenda();

                    a.setPrioridade(res.getString(1));
                    a.setIndice(res.getInt(2));
                    a.setIdPac(res.getInt(3));
                    a.setNomPac(res.getString(4));
                    a.setTipoConsulta(res.getString(5));
                    a.setConfirmacao(res.getBoolean(6));
                    a.setNomMed(res.getString(7));
                    a.setSituacao(res.getString(8));

                    lista.add(a);
                }
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao listar agenda.");
        } finally {
            fecharRecursos(null, psmt, null);
        }
        return lista;

    }

    @Override
    public List<Agenda> listarAgendaMed(String data, int matMatricula) throws SystemException {
        List<Agenda> lista = new ArrayList<Agenda>();

        try {
            psmt = conn.prepareStatement(PESQUISAR_AGENDA_MEDICA);
            psmt.setString(1, data);
            psmt.setInt(2, matMatricula);

            ResultSet res = psmt.executeQuery();

            if (res != null) {
                while (res.next()) {
                    Agenda a = new Agenda();

                    a.setPrioridade(res.getString(1));
                    a.setIdPac(res.getInt(2));
                    a.setNomPac(res.getString(3));
                    a.setTipoConsulta(res.getString(4));
                    a.setConfirmacao(res.getBoolean(5));
                    a.setSituacao(res.getString(6));
                    a.setIndice(res.getInt(7));

                    lista.add(a);
                }
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao listar agenda.");
        } finally {
            fecharRecursos(null, psmt, null);
        }
        return lista;
    }

    private String completarSql(Agenda filtroAge, Paciente filtroPac) throws SystemException {
        StringBuilder builder = new StringBuilder(" AND data = data ");

        if (!ObjetoUtil.isVazio(filtroAge.getDataInicio())) {
            builder.append(" && data >= " + "'" + filtroAge.getDataInicio() + "'");
        }

        if (!ObjetoUtil.isVazio(filtroAge.getDataFinal())) {
            builder.append(" && data <= " + "'" + filtroAge.getDataFinal() + "'");
        }

        if (!ObjetoUtil.isVazio(filtroAge.getIdConsulta())) {
            builder.append(" AND a.idConsulta " + igual(filtroAge.getIdConsulta().toString()));
        }

        if (!ObjetoUtil.isVazio(filtroPac.getNome())) {
            builder.append(" AND p.nome " + like(filtroPac.getNome()));
        }

        if (!ObjetoUtil.isVazio(filtroPac.getCpf())) {
            builder.append(" AND p.cpf " + igual(filtroPac.getCpf()));
        }

        if (!ObjetoUtil.isVazio(filtroPac.getDataNac())) {
            builder.append(" AND p.data_nasc " + igual(DataUtil.parseDate(filtroPac.getDataNac())));
        }

        return builder.toString();
    }

    @Override
    public List<Agenda> PesquisarAgendaAtendimento(Agenda filtroAge, Paciente filtroPac) throws SystemException {
        List<Agenda> lista = new ArrayList<Agenda>();

        try {
            psmt = conn.prepareStatement(PESQUISAR_ATENDIMENTO + completarSql(filtroAge, filtroPac));

            System.out.println(psmt);
            ResultSet res = psmt.executeQuery();

            if (res != null) {
                while (res.next()) {
                    Agenda a = new Agenda();

                    a.setIdConsulta(res.getInt(1));
                    a.setData(res.getString(2));
                    a.setPrioridade(res.getString(3));
                    a.setSituacao(res.getString(4));
                    a.setTipoConsulta(res.getString(5));

                    lista.add(a);
                }
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao listar agenda.");
        } finally {
            fecharRecursos(null, psmt, null);
        }
        return lista;
    }

}
