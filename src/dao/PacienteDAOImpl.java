package dao;

import dominio.Paciente;
import excecoes.DuplicadoException;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DataUtil;
import util.ObjetoUtil;

public class PacienteDAOImpl extends DAOAbstrato implements PacienteDAO {

    private static final String INSERIR = "INSERT INTO Paciente"
            + "( nome, nome_social, telefone, celular, email, cpf, rg, sexo, data_nasc, idade, estd_civil, endereco_idendereco) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ATUALIZAR = "UPDATE Paciente SET \n"
            + " nome=?, nome_social=?, telefone=?, celular=?, email=?, cpf=?, rg=?, \n"
            + " sexo=?, data_nasc=?, idade=?, estd_civil=?, endereco_idendereco=? \n"
            + " WHERE idPaciente=?";
    private static final String EXCLUIR = "DELETE FROM Paciente where idPaciente=? ";
    private static final String PESQUISAR = "SELECT * FROM Paciente ";

    public PacienteDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public void inserir(Paciente pac) throws DuplicadoException, SystemException {
        try {
            psmt = conn.prepareStatement(INSERIR, PreparedStatement.RETURN_GENERATED_KEYS);

            psmt.setString(1, pac.getNome());
            psmt.setString(2, pac.getNomeSocial());
            psmt.setString(3, pac.getTelefone());
            psmt.setString(4, pac.getCelular());
            psmt.setString(5, pac.getEmail());
            psmt.setString(6, pac.getCpf());
            if (ObjetoUtil.isVazio(pac.getRg())) {
                psmt.setNull(7, pac.getRg());
            } else {
                psmt.setInt(7, pac.getRg());
            }
            psmt.setString(8, pac.getSexo());
            psmt.setString(9, pac.getDataNac());
            if (ObjetoUtil.isVazio(pac.getIdade())) {
                psmt.setNull(10, pac.getIdade());
            } else {
                psmt.setInt(10, pac.getIdade());
            }
            psmt.setString(11, pac.getEstadoCivil());
            if (ObjetoUtil.isVazio(pac.getIdEndereco())) {
                psmt.setNull(12, pac.getIdEndereco());
            } else {
                psmt.setInt(12, pac.getIdEndereco());
            }

            int res = psmt.executeUpdate();

            if (res == 1) {
                rs = psmt.getGeneratedKeys();
                rs.next();
                pac.setIdPac(rs.getInt(1));
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao inserir Paciente");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
    }

    @Override
    public void atualizar(Paciente pac) throws NegocioException, SystemException {
        try {
            psmt = conn.prepareStatement(ATUALIZAR);
            psmt.setString(1, pac.getNome());
            psmt.setString(2, pac.getNomeSocial());
            psmt.setString(3, pac.getTelefone());
            psmt.setString(4, pac.getCelular());
            psmt.setString(5, pac.getEmail());
            psmt.setString(6, pac.getCpf());
            if (ObjetoUtil.isVazio(pac.getRg())) {
                psmt.setNull(7, pac.getRg());
            } else {
                psmt.setInt(7, pac.getRg());
            }
            psmt.setString(8, pac.getSexo());
            psmt.setString(9, pac.getDataNac());
            if (ObjetoUtil.isVazio(pac.getIdade())) {
                psmt.setNull(10, pac.getIdade());
            } else {
                psmt.setInt(10, pac.getIdade());
            }
            psmt.setString(11, pac.getEstadoCivil());
            if (ObjetoUtil.isVazio(pac.getIdEndereco())) {
                psmt.setNull(12, pac.getIdEndereco());
            } else {
                psmt.setInt(12, pac.getIdEndereco());
            }
            psmt.setInt(13, pac.getIdPac());
            psmt.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao atualizar Paciente");
        } finally {
            fecharRecursos(null, psmt, null);
        }
    }

    @Override
    public void excluir(Paciente pac) throws SystemException {
        try {
            psmt = conn.prepareStatement(EXCLUIR);
            psmt.setInt(1, pac.getIdPac());

            psmt.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao excluir Paciente");
        } finally {
            fecharRecursos(null, psmt, null);
        }
    }

    @Override
    public List<Paciente> listar() throws SystemException {
        List<Paciente> lista = new ArrayList<Paciente>();
        try {
            psmt = conn.prepareStatement(PESQUISAR);
            rs = psmt.executeQuery();

            while (rs.next()) {
                Paciente pac = new Paciente();

                pac.setIdPac(rs.getInt(1));
                pac.setNome(rs.getString(2));
                pac.setNomeSocial(rs.getString(3));
                pac.setTelefone(rs.getString(4));
                pac.setCpf(rs.getString(5));
                pac.setRg(rs.getInt(6));
                pac.setSexo(rs.getString(7));
                pac.setDataNac(rs.getString(8));
                pac.setIdade(rs.getInt(9));
                pac.setEstadoCivil(rs.getString(10));
                pac.setIdEndereco(rs.getInt(11));

                lista.add(pac);
            }
        } catch (SQLException e) {

        } finally {
            fecharRecursos(null, psmt, rs);
        }
        return lista;
    }

    @Override
    public List<Paciente> pesquisar(Paciente idPac) throws SystemException {

        return null;
    }

    @Override
    public List<Paciente> pesquiserFiltro(Paciente filtro) throws SystemException {
        List<Paciente> resultado = new ArrayList<Paciente>();

        try {
            psmt = conn.prepareStatement(PESQUISAR + completarSQL(filtro));

            rs = psmt.executeQuery();

            while (rs.next()) {
                Paciente p = new Paciente();

                p.setIdPac(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setNomeSocial(rs.getString(3));
                p.setTelefone(rs.getString(4));
                p.setCpf(rs.getString(5));
                p.setRg(rs.getInt(6));
                p.setSexo(rs.getString(7));
                p.setDataNac(rs.getString(8));
                p.setIdade(rs.getInt(9));
                p.setEstadoCivil(rs.getString(10));
                p.setIdEndereco(rs.getInt(11));
                p.setEmail(rs.getString(12));
                p.setCelular(rs.getString(13));

                resultado.add(p);
            }
        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao pesquisar Paciente.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
        return resultado;
    }

    @Override
    public List<Paciente> pesquiserFiltroAvancado(Paciente filtro) throws SystemException {
        List<Paciente> resultado = new ArrayList<Paciente>();

        try {
            psmt = conn.prepareStatement(PESQUISAR + completarSQLAvancado(filtro));

            rs = psmt.executeQuery();

            while (rs.next()) {
                Paciente p = new Paciente();

                p.setIdPac(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setNomeSocial(rs.getString(3));
                p.setTelefone(rs.getString(4));
                p.setCpf(rs.getString(5));
                p.setRg(rs.getInt(6));
                p.setSexo(rs.getString(7));
                p.setDataNac(rs.getString(8));
                p.setIdade(rs.getInt(9));
                p.setEstadoCivil(rs.getString(10));
                p.setIdEndereco(rs.getInt(11));
                p.setEmail(rs.getString(12));
                p.setCelular(rs.getString(13));

                resultado.add(p);
            }
        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao pesquisar Paciente.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
        return resultado;
    }

    private String completarSQL(Paciente filtro) {
        StringBuilder builder = new StringBuilder(" where 1=1 ");

        if (!ObjetoUtil.isVazio(filtro.getIdPac())) {
            builder.append(" and idPaciente " + igual(filtro.getIdPac().toString()));
        }

        if (!ObjetoUtil.isVazio(filtro.getNome())) {
            builder.append(" and nome " + like(filtro.getNome()));
        }

        if (!ObjetoUtil.isVazio(filtro.getNomeSocial())) {
            builder.append(" and nomeSocial" + like(filtro.getNomeSocial()));
        }

        if (!ObjetoUtil.isVazio(filtro.getCpf())) {
            builder.append(" and cpf " + igual(filtro.getCpf()));
        }

        if (!ObjetoUtil.isVazio(filtro.getRg())) {
            builder.append(" and rg " + igual(filtro.getRg().toString()));
        }

        if (!ObjetoUtil.isVazio(filtro.getSexo())) {
            builder.append(" and sexo " + like(filtro.getSexo()));
        }

        if (!ObjetoUtil.isVazio(filtro.getIdade())) {
            builder.append(" and idade " + igual(filtro.getIdade().toString()));
        }

        if (!ObjetoUtil.isVazio(filtro.getEstadoCivil())) {
            builder.append(" and estd_civil " + like(filtro.getEstadoCivil()));
        }
        return builder.toString();
    }

    public String completarSQLAvancado(Paciente filtro) throws NegocioException{
        StringBuilder builder = new StringBuilder(" where 1=1 ");

        if (!ObjetoUtil.isVazio(filtro.getIdPac())) {
            builder.append(" and idPaciente " + igual(filtro.getIdPac().toString()));
        }
        
        if (!ObjetoUtil.isVazio(filtro.getCpf())) {
            builder.append(" and cpf " + igual(filtro.getCpf()));
        }

        if (!ObjetoUtil.isVazio(filtro.getNome())) {
            builder.append(" and nome " + igual(filtro.getNome()));
        }

        if (!ObjetoUtil.isVazio(filtro.getDataNac())) {
            builder.append(" and data_nasc" + igual(DataUtil.parseDate(filtro.getDataNac())));
        }
        return builder.toString();
    }
}
