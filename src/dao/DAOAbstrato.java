package dao;

import excecoes.DuplicadoException;
import excecoes.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ObjetoUtil;

public abstract class DAOAbstrato {
    
    protected static final String INSERIR_FUN = "INSERT INTO funcionario (nome, cpf, rg, telefone, sexo, statuss, endereco_idEndereco) VALUES (?,?,?,?,?,?,?) ";

    protected Connection conn;
    protected PreparedStatement psmt;
    protected ResultSet rs;

    public DAOAbstrato(Connection conn) {
        if (ObjetoUtil.isNulo(conn)) {
            System.out.println("Connection null");
        }
        this.conn = conn;
    }

    public static void fecharRecursos(Connection conn, PreparedStatement psmt, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

        if (psmt != null) {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

        if (rs != null) {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }

    protected void tratarSQLException(SQLException e, String string) throws DuplicadoException, SystemException {
        String erro = e.getMessage();

        if (ObjetoUtil.isNulo(erro)) {
            erro = "";
        }

        if (erro.toLowerCase().indexOf("duplicate") >= 0) {
            throw new DuplicadoException("Id Duplicado");
        }
        throw new SystemException(string);
    }

    public String like(String valor) {
        return " like '%" + valor + "%'";
    }

    public String igual(String valor) {
        return " ='" + valor + "'";
    }
}
