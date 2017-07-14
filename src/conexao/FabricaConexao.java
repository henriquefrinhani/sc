		package conexao;

import java.sql.Connection;

import config.Config;

public final class FabricaConexao {

    private FabricaConexao() {
    }

   /*private static Conexao conexaoMsSql = new Conexao("com.mysql.jdbc.Driver", 
            "jdbc:mysql://127.0.0.1/smartclinTeste", "root", "root");
    */
    private static Conexao conexaoMsSql = new Conexao(//explorar esse código
            Config.getString("driver.mysql"),
            Config.getString("url.mysql"),
            Config.getString("usuario.mysql"),
            Config.getString("senha.mysql")
    );


    public static Connection getConnectionMysql() throws ConexaoException {
        return conexaoMsSql.getConnectin();
    }
}
