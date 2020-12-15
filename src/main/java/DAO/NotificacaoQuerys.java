/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.models.Notificacao;
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
public class NotificacaoQuerys implements IDAONotificacao{

    @Override
    public void solicitarPermissao(String imagem, String descricao) {
        ConexaoSQLite conexao= new ConexaoSQLite();
        boolean conectou = false;        
        try{
            
            ArrayList<UsuarioGenerico> admins;
            IDAOUsuario dao= new UsuarioQuerys();
            admins=dao.buscarUsuariosPorNivel("administrador");
            for(UsuarioGenerico usuario: admins){
                conectou=conexao.conectar();
                String sqlInsert = "INSERT INTO NOTIFICACOES(" +                            
                                "DESCRICAO," +
                                "REMETENTE," +
                                "DESTINATARIO,"+
                                "PATH"+
                                ") VALUES(?,?,?,?)"
                                + ";";

                PreparedStatement preparedStmt = conexao.criarPreparedStatement(sqlInsert);            
                preparedStmt.setString(1,descricao );
                preparedStmt.setString(2, UsuarioLogado.getInstancia().getLogin());
                preparedStmt.setString(3,usuario.getLogin());
                preparedStmt.setString(4,imagem);

                int resultado = preparedStmt.executeUpdate();
                if(resultado != 1 ){
                    System.out.println("admin nao notificado");
                }
                preparedStmt.close();
            }
            
        }            
        catch(SQLException e){
            System.err.println("erro na query: \n"+e.fillInStackTrace());
        }finally{
            if(conectou)
                conexao.desconectar();
        }
    }

    @Override
    public void notificarCompartilhamento(String imagem, String login) {
        ConexaoSQLite conexao= new ConexaoSQLite();
        boolean conectou = false;        
        try{
            conectou=conexao.conectar();
                String sqlInsert = "INSERT INTO NOTIFICACOES(" +                            
                                "DESCRICAO," +
                                "REMETENTE," +
                                "DESTINATARIO,"+
                                "PATH"+
                                ") VALUES(?,?,?,?)"
                                + ";";

                PreparedStatement preparedStmt = conexao.criarPreparedStatement(sqlInsert);            
                preparedStmt.setString(1,"Uma imagem foi compartilhada com você" );
                preparedStmt.setString(2, UsuarioLogado.getInstancia().getLogin());
                preparedStmt.setString(3,login);
                preparedStmt.setString(4,imagem);

                int resultado = preparedStmt.executeUpdate();
                if(resultado != 1 ){
                    System.out.println("usuario nao notificado");
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
    public ArrayList<Notificacao> obterNotificacoes() {
        ConexaoSQLite conexao= new ConexaoSQLite();
          
        boolean conectou=false;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        
        try{
            conectou=conexao.conectar();            
            String query = "SELECT * FROM NOTIFICACOES WHERE DESTINATARIO = ? AND (STATUS IS NULL OR STATUS=0)";
            stmt = conexao.criarPreparedStatement(query);
            stmt.setString(1, UsuarioLogado.getInstancia().getLogin());
            resultSet = stmt.executeQuery();
            ArrayList<Notificacao> notificacoes= new ArrayList();
            Notificacao notificacao;
            while(resultSet.next()){                                         
                notificacao = new Notificacao(resultSet.getString("DESCRICAO"), resultSet.getString("REMETENTE"),resultSet.getString("PATH"));
                notificacoes.add(notificacao);
            }
           return notificacoes;
        }catch(SQLException e){
            System.err.println("ERRO QUERY NOTIFICACOES : \n"+e.fillInStackTrace()); 
            return null;
        }finally{
            try{
                resultSet.close();
                stmt.close();
            }catch(SQLException e){
                System.out.println("ERRO QUERY NOTIFICACOES: \n"+e.fillInStackTrace()); 
            }
            if(conectou){
                conexao.desconectar();
            }
        }
    }

    @Override
    public void lerNotificacao(String descricao, String remetente, String path) {
        ConexaoSQLite conexao= new ConexaoSQLite();          
        boolean conectou=false;        
        PreparedStatement stmt = null;        
        try{
            conectou=conexao.conectar();            
            String query = "UPDATE NOTIFICACOES SET STATUS = ? WHERE DESCRICAO = ? AND REMETENTE = ? AND DESTINATARIO = ? AND PATH = ?";
            stmt = conexao.criarPreparedStatement(query);
            stmt.setBoolean(1, true);
            stmt.setString(2,descricao );
            stmt.setString(3,remetente );
            stmt.setString(4, UsuarioLogado.getInstancia().getLogin());
            stmt.setString(5,path );
            stmt.executeUpdate();
        }catch(SQLException e){
            System.err.println("erro sql ler notificação"+e.fillInStackTrace());
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
    public void excluirNotificacaoRedundante(String descricao, String remetente, String path){
        ConexaoSQLite conexao= new ConexaoSQLite();          
        boolean conectou=false;        
        PreparedStatement stmt = null;        
        try{
            conectou=conexao.conectar();            
            String query = "DELETE FROM NOTIFICACOES WHERE DESCRICAO = ? AND REMETENTE = ? AND PATH = ? AND STATUS IS NULL ";
            stmt = conexao.criarPreparedStatement(query);
            stmt.setString(1, descricao);
            stmt.setString(2, remetente);
            stmt.setString(3, path);
           
            
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
}
