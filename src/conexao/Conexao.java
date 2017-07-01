package conexao;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mensagem.Mensagem;

public class Conexao {

    private String driver;
    private String urlBanco;
    private String usuario;
    private String senha;

    public Conexao() {
    }

    public Conexao(String driver, String urlBanco, String usuario, String senha) {
        this.driver = driver;
        this.urlBanco = urlBanco;
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrlBanco() {
        return urlBanco;
    }

    public void setUrlBanco(String urlBanco) {
        this.urlBanco = urlBanco;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void SetSenha(String senha) {
        this.senha = senha;
    }

    public Connection getConnectin() throws ConexaoException {
        try {
            Class.forName(getDriver());
        } catch (ClassNotFoundException e) {
            throw new ConexaoException("Erro ao carregar o driver do banco de dados.\r\n" + e.getMessage());
        }

        try {
            return (Connection) DriverManager.getConnection(getUrlBanco(), getUsuario(), getSenha());

        } catch (SQLException e) {
            String erro = e.getMessage();
            if (erro == null) {
                erro = "";
            }
            erro = erro.trim().toLowerCase();
            if (erro.indexOf("user") != -1) {
                List<Mensagem> erros = new ArrayList<>();
                throw new ConexaoException("Usuário do Banco de Dados Inválido.");
            }

            throw new ConexaoException("Não foi possível conectar com o banco de dados.\r\n" );
        }
    }
}
