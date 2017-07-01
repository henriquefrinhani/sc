package dao;

import dominio.Administrador;
import dominio.Atendente;
import dominio.Login;
import dominio.Medico;
import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginDAOImpl extends DAOAbstrato implements LoginDAO {

    private static final String INSERIR = "INSERT INTO login (usuario, senha, perfil, funcionario_matricula, statuss) VALUES (?, ?, ?, ?, ?)";
    private static final String EXCLUIR = "";
    private static final String ALTENTICAR_LOGIN = "SELECT * FROM login WHERE usuario = ?";
    private static final String ALTENTICAR_SENHA = "SELECT senha FROM login WHERE idLogin = ?";
    private static final String CONSULTAR_LOGIN = "SELECT usuario FROM login WHERE usuario = ?";
    private static final String ALTERAR_SENHA = "UPDATE login SET senha = ? WHERE idLogin = ?";
    private static final String ACESSO_ATENDENTE = "SELECT nome, matricula FROM funcionario, login WHERE matricula=funcionario_matricula  and usuario=?";//rever
    private static final String ACESSO_MEDICO = "SELECT matricula, nome, login_idLogin FROM medico  WHERE login_idLogin=?";//rever
    private static final String ACESSO_ADMIN = "SELECT matricula, nome, login_idLogin FROM administrador  WHERE login_idLogin=?";//rever

    public LoginDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public void inserirLogin(Login l) throws SystemException, DuplicadoException {
        try {
            psmt = conn.prepareStatement(INSERIR);
            psmt.setString(1, l.getUsuario());
            psmt.setString(2, l.getSenha());
            psmt.setInt(3, l.getPerfil());
            psmt.setInt(4, l.getIdFuncionario());
            psmt.setBoolean(5, l.getStatus());

            
            
            
            System.out.println(psmt);
            int i = psmt.executeUpdate();

            if (i == 1) {
                rs = psmt.getGeneratedKeys();
                rs.next();
                l.setIdLogin(rs.getInt(1));
            }
        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao inserir Login.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
    }

    @Override
    public void excluirLogin(Login l) throws SystemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int altenticarLogin(Login l) throws SystemException {
        int perfil = 0;
        try {
            psmt = conn.prepareStatement(ALTENTICAR_LOGIN);
            psmt.setString(1, l.getUsuario());
            rs = psmt.executeQuery();

            while (rs.next()) {
                l.setIdLogin(rs.getInt("idLogin"));
                String use = rs.getString("usuario");
                String sen = rs.getString("senha");
                Integer per = rs.getInt("perfil");
                Boolean status = rs.getBoolean("statuss");

                if (use.equals(l.getUsuario()) && sen.equals(l.getSenha()) && status == true) {
                    perfil = per;
                }
            }
        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao Altenticar Login.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }

        return perfil;
    }

    @Override
    public boolean altenticarSenha(Login l) throws SystemException {
        boolean retorno = false;
        try {
            psmt = conn.prepareStatement(ALTENTICAR_SENHA);
            psmt.setInt(1, l.getIdLogin());
            psmt.executeQuery();

            while (rs.next()) {
                String sen = rs.getString("senha");
                System.out.println("senha: " + sen);
                if (sen.equals(l.getSenha())) {
                    retorno = true;
                }
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao antenticar Senha.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
        return retorno;
    }

    @Override
    public boolean isLogin(Login l) throws SystemException {
        boolean retorno = false;
        try {
            psmt = conn.prepareStatement(CONSULTAR_LOGIN);
            psmt.setString(1, l.getUsuario());
            psmt.executeQuery();

            while (rs.next()) {

                String log = rs.getString("usuario");

                if (log.equals(l.getUsuario())) {
                    retorno = true;
                }
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao pesquisar Login.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
        return retorno;
    }

    @Override
    public void alterarSenha(Login l) throws SystemException {
        try {
            psmt = conn.prepareStatement(ALTERAR_SENHA);
            psmt.setString(1, l.getNovaSenha());
            psmt.setInt(2, l.getIdLogin());
            psmt.executeUpdate();

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao alterar Senha.");
        } finally {
            fecharRecursos(null, psmt, null);
        }
    }

    @Override
    public Atendente acessoAtendente(String usuario) throws SystemException {
        Atendente a = new Atendente();
        try {
            psmt = conn.prepareStatement(ACESSO_ATENDENTE);
            psmt.setString(1, usuario);
            rs = psmt.executeQuery();

            while (rs.next()) {
                a.setNome(rs.getString(1));
                a.setMatricula(rs.getInt(2));
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao listar acesso de Atendente.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }

        return a;
    }

    @Override
    public Medico acessoMedico(int idLogin) throws SystemException {
        Medico m = new Medico();
        try {
            psmt = conn.prepareStatement(ACESSO_MEDICO);
            psmt.setInt(1, idLogin);

            if (rs != null) {
                while (rs.next()) {
                    m.setMatricula(rs.getInt(1));
                    m.setNome(rs.getString(2));
                    //    m.setIdLogin(rs.getInt(3));
                }
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao listar acesso de Medico.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
        return m;
    }

    @Override
    public Administrador acessoAdmin(int idLogin) throws SystemException {
        Administrador a = new Administrador();
        try {
            psmt = conn.prepareStatement(ACESSO_ADMIN);
            psmt.setInt(1, idLogin);

            if (rs != null) {
                while (rs.next()) {
                    a.setMatricula(rs.getInt(1));
                    a.setNome(rs.getString(2));
                    a.setIdLogin(rs.getInt(3));
                }
            }

        } catch (SQLException e) {
            tratarSQLException(e, "Erro ao listar acesso de Administrador.");
        } finally {
            fecharRecursos(null, psmt, rs);
        }
        return a;
    }

}
