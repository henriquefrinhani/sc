package dao;

import static dao.DAOAbstrato.fecharRecursos;
import dominio.Atendente;
import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ObjetoUtil;

public class AtendenteDAOImpl extends DAOAbstrato implements AtendenteDAO {

    private static final String INSERIR = "INSERT INTO atendente (funcionario_matricula) VALUES ( LAST_INSERT_ID())";
    private static final String ATUALIZAR = "UPDATE funcionario SET nome=?, cpf=?, rg=?, telefone=?, "
            + " sexo=?, endereco_idEndereco=? where  matricula=?";
    private static final String EXCLUIR = "DELETE FROM funcionario, atendente using funcionario inner join atendente "
            + "WHERE matricula=funcionario_matricula and matricula=?;";
    private static final String PESQUISAR = "SELECT * FROM Funcionario, atendente WHERE matricula= funcionario_matricula ";

    public AtendenteDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public void inserirAtendente(Atendente ate) throws SystemException, DuplicadoException {
        try {
            psmt = conn.prepareStatement(INSERIR_FUN);
            psmt.setString(1, ate.getNome());
            psmt.setString(2, ate.getCpf());
            psmt.setInt(3, ate.getRg());
            psmt.setString(4, ate.getTelefone());
            psmt.setString(5, ate.getSexo());
            psmt.setBoolean(6, ate.getStatus());
            psmt.setInt(7, ate.getIdEndereco());

            int res = psmt.executeUpdate();

            if (res == 1) {
                rs = psmt.getGeneratedKeys();
                rs.next();
                ate.setMatricula(rs.getInt(1));
            }

            PreparedStatement psmt2 = conn.prepareStatement(INSERIR);
            psmt2.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao inserir Atendente.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
    }

    @Override
    public void atualizarAtendente(Atendente ate) throws SystemException, DuplicadoException {
        try {
            psmt = conn.prepareStatement(ATUALIZAR);
            psmt.setString(1, ate.getNome());
            psmt.setString(2, ate.getCpf());
            psmt.setInt(3, ate.getRg());
            psmt.setString(4, ate.getTelefone());
            psmt.setString(5, ate.getSexo());
            psmt.setInt(6, ate.getIdEndereco());
            psmt.setInt(7, ate.getMatricula());

            psmt.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao atualizar Atendente.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
    }

    @Override
    public void excluirAtendente(Atendente ate) throws SystemException {
        try {
            psmt = conn.prepareStatement(EXCLUIR);
            psmt.setInt(1, ate.getMatricula());
            psmt.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao excluir Atendente");
        } finally {
            fecharRecursos(null, psmt, null);
        }
    }

    @Override
    public List<Atendente> pesquisarAtendente(Atendente filtro) throws SystemException {
        List<Atendente> lista = new ArrayList<>();
        try {
            psmt = conn.prepareStatement(PESQUISAR + completarSQL(filtro));
            rs = psmt.executeQuery();

            while (rs.next()) {
                Atendente a = new Atendente();

                a.setMatricula(rs.getInt(1));
                a.setNome(rs.getString(2));
                a.setCpf(rs.getString(3));
                a.setRg(rs.getInt(4));
                a.setTelefone(rs.getString(5));
                a.setSexo(rs.getString(6));
                a.setStatus(rs.getBoolean(7));
                a.setIdEndereco(rs.getInt(8));

                lista.add(a);
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao pesquisar Atendente.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
        return lista;
    }

    private String completarSQL(Atendente filtro) {
        StringBuilder builder = new StringBuilder();

        if (!ObjetoUtil.isVazio(filtro.getMatricula())) {
            builder.append(" and matricula " + igual(filtro.getMatricula().toString()));
        }

        if (!ObjetoUtil.isVazio(filtro.getNome())) {
            builder.append(" and nome " + like(filtro.getNome()));
        }
        if (!ObjetoUtil.isVazio(filtro.getCpf())) {
            builder.append(" and cpf" + igual(filtro.getCpf()));
        }

        if (!ObjetoUtil.isVazio(filtro.getRg())) {
            builder.append(" and rg " + igual(filtro.getRg().toString()));
        }

        if (!ObjetoUtil.isVazio(filtro.getSexo())) {
            builder.append(" and sexo " + like(filtro.getSexo()));
        }

        if (!ObjetoUtil.isVazio(filtro.getTelefone())) {
            builder.append(" and telefone " + like(filtro.getTelefone()));
        }

        return builder.toString();
    }

    @Override
    public Atendente pesquisarMatricula(int matricula) throws SystemException {
        Atendente ate = new Atendente();
        try {
            psmt = conn.prepareStatement(PESQUISAR +" and matricula="+matricula);
            rs = psmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    ate.setMatricula(rs.getInt(1));
                    ate.setNome(rs.getString(2));
                    ate.setCpf(rs.getString(3));
                    ate.setRg(rs.getInt(4));
                    ate.setTelefone(rs.getString(5));
                    ate.setSexo(rs.getString(6));
                    ate.setIdEndereco(rs.getInt(7));
                }
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao listar Atendente.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
        return ate;
    }
}
