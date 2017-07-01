
package testes;

import conexao.ConexaoException;
import conexao.FabricaConexao;
import excecoes.NegocioException;
import excecoes.SystemException;
import java.sql.Connection;



public class TesteConexao  {

    public static void main(String[] args) throws  SystemException, NegocioException{
        try  {
            Connection conn = FabricaConexao.getConnectionMysql();
            System.out.println("CONECTOU");
        }catch(ConexaoException e){
            System.out.println(e.getMessage());
        }
    }
   
}