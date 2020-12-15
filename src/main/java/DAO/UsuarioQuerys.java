/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.usuario.Administrador;
import Model.usuario.Comum;
import Model.usuario.UsuarioGenerico;
import Model.usuario.UsuarioLogado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Thiago
 */
public class UsuarioQuerys implements IDAOUsuario{
    
    @Override
    public void addUsuario(String pNome, String pSenha, String pNivel) {
        ConexaoSQLite conexao= new ConexaoSQLite();
              boolean conectou = false;        
        try{
            conectou=conexao.conectar();
            String sqlInsert = "INSERT INTO USUARIOS(" +                            
                            "LOGIN," +
                            "SENHA," +
                            "NIVEL"+
                            ") VALUES(?,?,?)"
                            + ";";
            
            PreparedStatement preparedStmt = conexao.criarPreparedStatement(sqlInsert);            
            preparedStmt.setString(1, pNome);
            preparedStmt.setString(2, pSenha);
            preparedStmt.setString(3, pNivel);
                     
            int resultado = preparedStmt.executeUpdate();
            if(resultado != 1 ){
                System.out.println("pessoa nao inserida");
            }
            preparedStmt.close();
        }            
        catch(SQLException e){
            System.err.println("erro na query: \n"+e.fillInStackTrace());
        }finally{
            if(conectou)
                conexao.desconectar();
        }
    }

    @Override
    public UsuarioGenerico efetuarLogin(String login, String senha) {
        ConexaoSQLite conexao= new ConexaoSQLite();
          
        boolean conectou=false;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        
        try{
            conectou=conexao.conectar();            
            String query = "SELECT * FROM USUARIOS WHERE LOGIN = ? AND SENHA = ?";
            stmt = conexao.criarPreparedStatement(query);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            resultSet = stmt.executeQuery();
            if(resultSet.next()){
                         UsuarioLogado.criaInstancia(resultSet.getString("LOGIN"),                        
                         resultSet.getString("SENHA"),
                         resultSet.getString("NIVEL"));
                return UsuarioLogado.getInstancia();
            }
            else{
                return null;
            }
        }catch(SQLException e){
            System.err.println("ERRO QUERY LOGIN: \n"+e.fillInStackTrace());
            return null;
        }finally{
            try{
                resultSet.close();
                stmt.close();
            }catch(SQLException e){
                System.out.println("ERRO QUERY LOGIN2: \n"+e.fillInStackTrace()); 
            }
            if(conectou){
                conexao.desconectar();
            }
        }
    }

    @Override
    public boolean isEmpty(){
        ConexaoSQLite conexao= new ConexaoSQLite();
          
        boolean conectou=false;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        
        try{
            conectou=conexao.conectar();            
            String query = "SELECT * FROM USUARIOS";
            stmt = conexao.criarPreparedStatement(query);
            resultSet = stmt.executeQuery();
            if(resultSet.next()){                         
                System.out.println("tem usuarios");
                return false;
            }
            else{
                System.out.println("nao tem usuarios");
                return true;
            }
        }catch(SQLException e){
            System.err.println("ERRO QUERY LOGIN 3: \n"+e.fillInStackTrace());
            return false;
        }finally{
            try{
                resultSet.close();
                stmt.close();
            }catch(SQLException e){
                System.out.println("ERRO QUERY LOGIN4: \n"+e.fillInStackTrace()); 
            }
            if(conectou){
                conexao.desconectar();
            }
        }
    }

    @Override
    public ArrayList<UsuarioGenerico> buscarUsuarios() {
        ConexaoSQLite conexao= new ConexaoSQLite();
          
        boolean conectou=false;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        
        try{
            conectou=conexao.conectar();            
            String query = "SELECT * FROM USUARIOS WHERE LOGIN <> ?";
            stmt = conexao.criarPreparedStatement(query);
            stmt.setString(1, UsuarioLogado.getInstancia().getLogin());
            resultSet = stmt.executeQuery();
            ArrayList<UsuarioGenerico> usuarios= new ArrayList();
            while(resultSet.next()){                                         
                if(resultSet.getString("NIVEL").equals("comum")){
                  usuarios.add(new Comum().criarUsuario(resultSet.getString("LOGIN"),
                          resultSet.getString("SENHA"),
                          resultSet.getString("NIVEL")));
                }else
                {
                    usuarios.add(new Administrador().criarUsuario(resultSet.getString("LOGIN"),
                          resultSet.getString("SENHA"),
                          resultSet.getString("NIVEL")));
                }
            }
           return usuarios;
        }catch(SQLException e){
            System.err.println("ERRO QUERY LOGIN 3: \n"+e.fillInStackTrace()); 
            return null;
        }finally{
            try{
                resultSet.close();
                stmt.close();
            }catch(SQLException e){
                System.out.println("ERRO QUERY LOGIN4: \n"+e.fillInStackTrace()); 
            }
            if(conectou){
                conexao.desconectar();
            }
        }
    }

    @Override
    public void editarUsuario(String login, String nivel) {
        ConexaoSQLite conexao= new ConexaoSQLite();
        boolean conectou = false;
        try{              
            conectou=conexao.conectar();            
            String sqlUpdate = "UPDATE USUARIOS SET NIVEL = ?"
                    + "WHERE LOGIN = ?";             
            
            PreparedStatement preparedStmt = conexao.criarPreparedStatement(sqlUpdate);
            if(preparedStmt==null){
                System.out.println("editar usuario nao criou o stmt");
            }
            preparedStmt.setString(1,nivel);
            preparedStmt.setString(2,login);                
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }            
        catch(SQLException e){
            System.err.println("sql deu ruim");
        }finally{
            if(conectou)
                conexao.desconectar();
        }
    }

    @Override
    public void excluirUsuario(String login) {
        ConexaoSQLite conexao= new ConexaoSQLite();          
        boolean conectou=false;        
        PreparedStatement stmt = null;        
        try{
            conectou=conexao.conectar();            
            String query = "DELETE FROM USUARIOS WHERE LOGIN = ? ";
            stmt = conexao.criarPreparedStatement(query);
            stmt.setString(1, login);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.err.println("erro sql excluir usuario"+e.fillInStackTrace());
            //return false;
        }finally{
            try{
                stmt.close();
            }catch(SQLException e){
                System.out.println("dao.FuncionarioDAO.buscarFuncionario() reulset..."); 
            }
            if(conectou){
                conexao.desconectar();
                //System.out.println("fechou a conexao");
            }
        }
        //return true;
    }

    @Override
    public ArrayList<UsuarioGenerico> buscarUsuariosPorNivel(String nivel) {
          ConexaoSQLite conexao= new ConexaoSQLite();
          
        boolean conectou=false;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        
        try{
            conectou=conexao.conectar();            
            String query = "SELECT * FROM USUARIOS WHERE NIVEL = ? AND LOGIN <> ?";
            stmt = conexao.criarPreparedStatement(query);
            stmt.setString(1, nivel);
            stmt.setString(2, UsuarioLogado.getInstancia().getLogin());
            resultSet = stmt.executeQuery();
            ArrayList<UsuarioGenerico> usuarios= new ArrayList();
            while(resultSet.next()){                                         
                if(resultSet.getString("NIVEL").equals("comum")){
                  usuarios.add(new Comum().criarUsuario(resultSet.getString("LOGIN"),
                          resultSet.getString("SENHA"),
                          resultSet.getString("NIVEL")));
                }else
                {
                    usuarios.add(new Administrador().criarUsuario(resultSet.getString("LOGIN"),
                          resultSet.getString("SENHA"),
                          resultSet.getString("NIVEL")));
                }
            }
           return usuarios;
        }catch(SQLException e){
            System.err.println("ERRO QUERY LOGIN 3: \n"+e.fillInStackTrace()); 
            return null;
        }finally{
            try{
                resultSet.close();
                stmt.close();
            }catch(SQLException e){
                System.out.println("ERRO QUERY LOGIN4: \n"+e.fillInStackTrace()); 
            }
            if(conectou){
                conexao.desconectar();
            }
        }
    }
}