package dao;

import dominio.Administrador;
import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ObjetoUtil;

public class AdministradorDAOImpl extends DAOAbstrato implements AdministradorDAO {

    private static final String INSERIR = "INSERT INTO Administrador "
            + "(nome, cpf, rg, sexo, telefone, endereco_idEndereco, login_idLogin) "
            + "VALUES (?,?,?,?,?,?,?) ";
    private static final String ATUALIZAR = "UPDATE Administrador SET nome=?, cpf=?, rg=?, "
            + "sexo=?, telefone=?, endereco_idEndereco=?, login_idLogin=? WHERE matricula=?";
    private static final String EXCLUIR = "DELETE FROM Administrador WHERE matricula=?";
    private static final String PESQUISAR = "SELECT * FROM Administrador";

    public AdministradorDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public void inserirAdmin(Administrador adm) throws SystemException, DuplicadoException {
        try {
            psmt = conn.prepareStatement(INSERIR);
            psmt.setString(1, adm.getNome());
            psmt.setString(2, adm.getCpf());
            psmt.setInt(3, adm.getRg());
            psmt.setString(4, adm.getSexo());
            psmt.setString(5, adm.getTelefone());
            psmt.setInt(6, adm.getIdEndereco());
            psmt.setInt(7, adm.getIdLogin());
            int i = psmt.executeUpdate();

            if (i == 1) {
                rs = psmt.getGeneratedKeys();
                rs.next();
                adm.setMatricula(rs.getInt(1));
            }
        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao inserir Administrador.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
    }

    @Override
    public void atualizarAdmin(Administrador adm) throws SystemException, DuplicadoException {
        try {
            psmt = conn.prepareStatement(ATUALIZAR);
            psmt.setString(1, adm.getNome());
            psmt.setString(2, adm.getCpf());
            psmt.setInt(3, adm.getRg());
            psmt.setString(4, adm.getSexo());
            psmt.setString(5, adm.getTelefone());
            psmt.setInt(6, adm.getIdEndereco());
            psmt.setInt(7, adm.getIdLogin());
            psmt.setInt(8, adm.getMatricula());
            psmt.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao atualizar Administrador.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
    }

    @Override
    public void excluirAdmin(Administrador adm) throws SystemException {
        try {
            psmt = conn.prepareStatement(EXCLUIR);
            psmt.setInt(1, adm.getMatricula());
            psmt.executeUpdate();
        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao excluir Administrador.");
        } finally {
            fecharRecursos(null, psmt, null);
        }
    }

    @Override
    public List<Administrador> listaAdmin() throws SystemException {
        List<Administrador> lista = new ArrayList<>();
        try {
            psmt = conn.prepareStatement(PESQUISAR);
            rs = psmt.executeQuery();

            while (rs.next()) {
                Administrador a = new Administrador();
                a.setMatricula(rs.getInt(1));
                a.setNome(rs.getString(2));
                a.setCpf(rs.getString(3));
                a.setRg(rs.getInt(4));
                a.setSexo(rs.getString(5));
                a.setTelefone(rs.getString(6));
                a.setIdEndereco(rs.getInt(7));
                a.setIdLogin(rs.getInt(8));

                lista.add(a);
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao listar Administrador.");
        } finally {
            fecharRecursos(null, psmt, null);
        }

        return lista;
    }

    @Override
    public List<Administrador> pesquisa(Administrador filtro) throws SystemException {
        List<Administrador> lista = new ArrayList<>();
        try {
            psmt = conn.prepareStatement(PESQUISAR + completarSQL(filtro));
            rs = psmt.executeQuery();

            while (rs.next()) {
                Administrador a = new Administrador();
                a.setMatricula(rs.getInt(1));
                a.setNome(rs.getString(2));
                a.setCpf(rs.getString(3));
                a.setRg(rs.getInt(4));
                a.setSexo(rs.getString(5));
                a.setTelefone(rs.getString(6));
                a.setIdEndereco(rs.getInt(7));
                a.setIdLogin(rs.getInt(8));

                lista.add(a);
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao pesquisar Administrador.");
        } finally {
            fecharRecursos(null, psmt, null);
        }

        return lista;
    }

    private String completarSQL(Administrador filtro) {
        StringBuilder builder = new StringBuilder(" WHERE 1=1");

        if (!ObjetoUtil.isVazio(filtro.getMatricula())) {
            builder.append(" and matricula" + igual(filtro.getMatricula().toString()));
        }

        if (!ObjetoUtil.isVazio(filtro.getNome())) {
            builder.append(" and nome").append(like(filtro.getNome()));
        }

        if (!ObjetoUtil.isVazio(filtro.getCpf())) {
            builder.append(" and cpf").append(igual(filtro.getCpf()));
        }

        if (!ObjetoUtil.isVazio(filtro.getRg())) {
            builder.append(" and rg").append(igual(filtro.getRg().toString()));
        }
        if (!ObjetoUtil.isVazio(filtro.getSexo())) {
            builder.append(" and sexo").append(igual(filtro.getSexo()));
        }

        if (!ObjetoUtil.isVazio(filtro.getTelefone())) {
            builder.append(" and telefone").append(igual(filtro.getTelefone()));
        }
        return builder.toString();
    }
}
