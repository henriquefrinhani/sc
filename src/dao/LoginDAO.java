package dao;

import dominio.Administrador;
import dominio.Atendente;
import dominio.Login;
import dominio.Medico;
import excecoes.DuplicadoException;
import excecoes.SystemException;

public interface LoginDAO {
    public void inserirLogin(Login l) throws SystemException, DuplicadoException;
    public void excluirLogin(Login l) throws SystemException;
    public int altenticarLogin(Login l) throws SystemException;
    public boolean altenticarSenha(Login l) throws SystemException;
    public boolean isLogin(Login l) throws SystemException;
    public void alterarSenha(Login l) throws SystemException;
    public Atendente acessoAtendente(String usuario) throws SystemException;
    public Medico acessoMedico(int idLogin) throws SystemException;
    public Administrador acessoAdmin (int idLogin) throws SystemException;
    
}
