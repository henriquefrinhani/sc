package dao;

import dominio.Endereco;
import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EnderecoDAOImpl extends DAOAbstrato implements EnderecoDAO {

    private static final String INSERIR = "INSERT INTO endereco(complemento, cep, uf, rua, cidade) \n"
            + " VALUES (?,?,?,?,?)";
    private static final String ATUALIZAR = "UPDATE endereco SET complemento=?, cep=?, uf=?, rua=?, cidade=? WHERE idEndereco=?";
    private static final String EXCLUIR = "";
    private static final String PESQUISAR = "SELECT * FROM endereco ";

    public EnderecoDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public void inserirEnd(Endereco end) throws SystemException, DuplicadoException {
        try {
            psmt = conn.prepareStatement(INSERIR);
            psmt.setString(1, end.getComplemento());
            psmt.setString(2, end.getCep());
            psmt.setString(3, end.getUf());
            psmt.setInt(4, end.getRua());
            psmt.setString(5, end.getCidade());
            
            int res = psmt.executeUpdate();

            if (res == 1) {
                rs = psmt.getGeneratedKeys();
                rs.next();
                end.setIdEndereco(rs.getInt(1));
            }

        } catch (SQLException ex) {
            tratarSQLException(ex, "Erro ao inserir endereço.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }

    }

    @Override
    public void atualizarEnd(Endereco end) throws SystemException, DuplicadoException {

        try {
            psmt = conn.prepareStatement(ATUALIZAR);

            psmt.setString(1, end.getComplemento());
            psmt.setString(2, end.getCep());
            psmt.setString(3, end.getUf());
            psmt.setInt(4, end.getRua());
            psmt.setString(5, end.getCidade());
            psmt.setInt(6, end.getIdEndereco());
            psmt.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao atualizar endereço.");
        } finally {
            fecharRecursos(null, psmt, null);
        }

    }

    @Override
    public void excluirEnd(Endereco end) throws SystemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Endereco> pesquisa(Endereco filtro) throws SystemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Endereco buscarEndereco(int idEndereco) throws SystemException {
        Endereco end = new Endereco();
        try {
            psmt = conn.prepareStatement(PESQUISAR + " WHERE idEndereco=" + idEndereco);
            rs = psmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    end.setIdEndereco(rs.getInt(1));
                    end.setComplemento(rs.getString(2));
                    end.setCep(rs.getString(3));
                    end.setUf(rs.getString(4));
                    end.setRua(rs.getInt(5));
                    end.setCidade(rs.getString(6));
                }
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao listar Endereço.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
        return end;
    }

}
