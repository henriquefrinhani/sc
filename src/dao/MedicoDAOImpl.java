package dao;

import dominio.Medico;
import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ObjetoUtil;

public class MedicoDAOImpl extends DAOAbstrato implements MedicoDAO {

    private static final String INSERIR = "INSERT INTO medico (crm, especialidade_idEspecialidade, funcionario_matricula) "
            + " VALUES (?, ?, LAST_INSERT_ID())";

    private static final String ATUALIZAR = "UPDATE funcionario, medico  SET nome=?, cpf=?, rg=?, telefone=?, "
            + " sexo=?, endereco_idEndereco=?, crm=?, statuss=?, especialidade_idEspecialidade=? "
            + " where matricula=funcionario_matricula and matricula=?";
    private static final String EXCLUIR = "DELETE FROM funcionario, medico using funcionario inner join medico "
            + "WHERE matricula=funcionario_matricula and matricula=?;";
    private static final String PESQUISAR = "SELECT * FROM Funcionario, Medico WHERE matricula= funcionario_matricula ";

    public MedicoDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public void inserir(Medico med) throws SystemException, DuplicadoException {
        try {
            psmt = conn.prepareStatement(INSERIR_FUN);
            psmt.setString(1, med.getNome());
            psmt.setString(2, med.getCpf());
            psmt.setInt(3, med.getRg());
            psmt.setString(4, med.getTelefone());
            psmt.setString(5, med.getSexo());
            psmt.setBoolean(6, med.isStatus());
            psmt.setInt(7, med.getIdEndereco());

            int res = psmt.executeUpdate();

            if (res == 1) {
                rs = psmt.getGeneratedKeys();
                rs.next();
                med.setMatricula(rs.getInt(1));
            }

            PreparedStatement psmt2 = conn.prepareStatement(INSERIR);
            psmt2.setString(1, med.getCrm());
            psmt2.setInt(2, med.getIdEspecialidade());
            psmt2.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao inserir Médico.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
    }

    @Override
    public void atualizar(Medico med) throws SystemException, DuplicadoException {
        try {
            psmt = conn.prepareStatement(ATUALIZAR);
            psmt.setString(1, med.getNome());
            psmt.setString(2, med.getCpf());
            psmt.setInt(3, med.getRg());
            psmt.setString(4, med.getTelefone());
            psmt.setString(5, med.getSexo());
            psmt.setInt(6, med.getIdEndereco());
            psmt.setString(7, med.getCrm());
            psmt.setBoolean(8, med.isStatus());
            psmt.setInt(9, med.getIdEspecialidade());
            psmt.setInt(10, med.getMatricula());

            psmt.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao atualizar Médico.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
    }

    @Override
    public void excliur(Medico med) throws SystemException {
        try {
            psmt = conn.prepareStatement(EXCLUIR);
            psmt.setInt(1, med.getMatricula());
            psmt.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao excluir Médico.");
        } finally {
            fecharRecursos(null, psmt, null);
        }
    }

    @Override
    public List<Medico> lista() throws SystemException {
        List<Medico> lista = new ArrayList<>();

        try {
            psmt = conn.prepareStatement(PESQUISAR);
            rs = psmt.executeQuery();

            while (rs.next()) {
                Medico m = new Medico();

                m.setMatricula(rs.getInt(1));
                m.setNome(rs.getString(2));
                m.setCpf(rs.getString(3));
                m.setRg(rs.getInt(4));
                m.setTelefone(rs.getString(5));
                m.setSexo(rs.getString(6));
                m.setIdEndereco(rs.getInt(7));
                m.setCrm(rs.getString(8));
                m.setStatus(rs.getBoolean(9));
                m.setIdEspecialidade(rs.getInt(10));

                lista.add(m);
            }
        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao listar Médico.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
        return lista;
    }

    @Override

    public List<Medico> pesquisar(Medico filtro) throws SystemException {
        List<Medico> pesquisa = new ArrayList<>();

        try {
            psmt = conn.prepareStatement(PESQUISAR + completarSQL(filtro));
            rs = psmt.executeQuery();

            while (rs.next()) {
                Medico m = new Medico();

                m.setMatricula(rs.getInt(1));
                m.setNome(rs.getString(2));
                m.setCpf(rs.getString(3));
                m.setRg(rs.getInt(4));
                m.setTelefone(rs.getString(5));
                m.setSexo(rs.getString(6));
                m.setIdEndereco(rs.getInt(7));
                m.setCrm(rs.getString(8));
                m.setStatus(rs.getBoolean(9));
                m.setIdEspecialidade(rs.getInt(10));

                pesquisa.add(m);
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao pesquisar Médico.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
        return pesquisa;
    }

    private String completarSQL(Medico filtro) {
        StringBuilder builder = new StringBuilder();

        if (!ObjetoUtil.isVazio(filtro.getMatricula())) {
            builder.append(" and matricula " + igual(filtro.getMatricula().toString()));
        }

        if (!ObjetoUtil.isVazio(filtro.getNome())) {
            builder.append(" and nome " + like(filtro.getNome()));
        }

        if (!ObjetoUtil.isVazio(filtro.getCpf())) {
            builder.append(" and cpf " + igual(filtro.getCpf()));
        }

        if (!ObjetoUtil.isVazio(filtro.getRg())) {
            builder.append(" and rg" + igual(filtro.getRg().toString()));
        }
        if (!ObjetoUtil.isVazio(filtro.getCrm())) {
            builder.append(" and crm" + igual(filtro.getCrm()));
        }

        if (!ObjetoUtil.isVazio(filtro.getSexo())) {
            builder.append(" and sexo" + like(filtro.getSexo()));
        }

        return builder.toString();
    }
}
