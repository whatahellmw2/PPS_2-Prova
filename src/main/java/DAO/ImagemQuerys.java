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
public class ImagemQuerys implements IDAOImagens{

    @Override
    public boolean verificarAcesso(String nomeImagem) {
        ConexaoSQLite conexao= new ConexaoSQLite();
          
        boolean conectou=false;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        try{
            conectou=conexao.conectar();            
            String query = "SELECT * FROM ACESSO_IMAGEM WHERE LOGIN = ? AND PATH = ?";
            stmt = conexao.criarPreparedStatement(query);
            stmt.setString(1, UsuarioLogado.getInstancia().getLogin());
            stmt.setString(2, nomeImagem);
            resultSet = stmt.executeQuery();
            
            if(resultSet.next()){                                         
              return true; 
            }else{
              return false;
            }

        }catch(SQLException e){
            System.err.println("ERRO QUERY AcessoImagem 1: \n"+e.fillInStackTrace()); 
            return false;
        }finally{
            try{
                resultSet.close();
                stmt.close();
            }catch(SQLException e){
                System.out.println("ERRO QUERY AcessoImagem 2: \n"+e.fillInStackTrace()); 
            }
            if(conectou){
                conexao.desconectar();
            }
        }
    }
    
}
