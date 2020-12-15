
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hiago
 */
public class ConexaoSQLite {
    public Connection conexao;
    
    public boolean conectar(){
        try{
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:.\\banco\\sqliteProva.db";
            this.conexao = DriverManager.getConnection(url);
        }catch(SQLException e){
            System.err.println("método conectar: \n"+e.getMessage()+e.fillInStackTrace());
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoSQLite.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("método conectar2: \n"+ex.getMessage()+ex.fillInStackTrace());
        }
        return true;
    }
   
    public boolean desconectar(){
        try{
            if(this.conexao.isClosed()==false){
                this.conexao.close();
            }
        }catch(SQLException e){
            System.err.println("método desconectar: \n"+e.getMessage()+e.fillInStackTrace());
            return false;
        }
        return true;
    }
//    //
//    public Statement criarStatement(){
//        try{
//            return this.conexao.createStatement();
//        }catch(SQLException e){
//            return null;
//        }
//    }
    //para executar os sqls
    public PreparedStatement criarPreparedStatement(String sql){
        try{
            return this.conexao.prepareStatement(sql);
        }catch(SQLException e){
            System.out.println("Método criarPreparedStatement: \n" + e.fillInStackTrace());
            return null;
        }
    }
    public Connection getConexao(){
        return this.conexao;
    }
}
