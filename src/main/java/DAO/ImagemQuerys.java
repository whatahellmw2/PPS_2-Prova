/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Model.usuario.UsuarioLogado;
import business.MudaVisibilidadeArquivo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    @Override
    public int verificarNivelDePermissão(String nomeImagem, String nomeUsuario) {
        ConexaoSQLite conexao= new ConexaoSQLite();
          
        boolean conectou=false;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        try{
            conectou=conexao.conectar();            
            String query = "SELECT * FROM ACESSO_IMAGEM WHERE LOGIN = ? AND PATH = ?";
            stmt = conexao.criarPreparedStatement(query);
            stmt.setString(1, nomeUsuario);
            stmt.setString(2, nomeImagem);
            resultSet = stmt.executeQuery();
            
            if(resultSet.next()){                                         
              return resultSet.getInt("NIVEL"); 
            }else{
              return -1;
            }

        }catch(SQLException e){
            System.err.println("ERRO QUERY AcessoImagem 1: \n"+e.fillInStackTrace()); 
            return -1;
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

    @Override
    public void adicionarAcesso(String nomeImagem, String nomeUsuario) {
        ConexaoSQLite conexao= new ConexaoSQLite();
              boolean conectou = false;        
        try{
            conectou=conexao.conectar();
            String sqlInsert = "INSERT INTO ACESSO_IMAGEM(" +                            
                            "PATH," +
                            "LOGIN," +
                            "NIVEL"+
                            ") VALUES(?,?,?)"
                            + ";";
            
            PreparedStatement preparedStmt = conexao.criarPreparedStatement(sqlInsert);            
            preparedStmt.setString(1, nomeImagem);
            preparedStmt.setString(2, nomeUsuario);
            preparedStmt.setString(3, "1");
                     
            int resultado = preparedStmt.executeUpdate();
            if(resultado != 1 ){
                System.out.println("erro na query de acesso 1: \n");
            }
            preparedStmt.close();
        }            
        catch(SQLException e){
            System.err.println("erro na query de adicionar acesso2: \n"+e.fillInStackTrace());
        }finally{
            if(conectou)
                conexao.desconectar();
        }
    }

    @Override
    public void excluirImagem(String nomeImagem) {
        ConexaoSQLite conexao= new ConexaoSQLite();          
        boolean conectou=false;        
        PreparedStatement stmt = null;        
        try{
            conectou=conexao.conectar();            
            String query = "DELETE FROM IMAGEM WHERE PATH = ? ";
            stmt = conexao.criarPreparedStatement(query);
            stmt.setString(1, nomeImagem);
            stmt.executeUpdate();
            
            query="DELETE FROM ACESSO_IMAGEM WHERE PATH = ? ";
            stmt = conexao.criarPreparedStatement(query);
            stmt.setString(1, nomeImagem);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.err.println("erro sql excluir imagem"+e.fillInStackTrace());
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
    public void adicionarPermissão(String nomeImagem,String nomeUsuarios, int nivel) {
         ConexaoSQLite conexao= new ConexaoSQLite();
              boolean conectou = false;        
        try{
            conectou=conexao.conectar();
            String sqlInsert = "INSERT INTO ACESSO_IMAGEM(" +                            
                            "PATH," +
                            "LOGIN," +
                            "NIVEL"+
                            ") VALUES(?,?,?)"
                            + ";";
            
            PreparedStatement preparedStmt = conexao.criarPreparedStatement(sqlInsert);            
            preparedStmt.setString(1, nomeImagem);
            preparedStmt.setString(2, nomeUsuarios);
            preparedStmt.setInt(3,nivel );
                     
            int resultado = preparedStmt.executeUpdate();
            if(resultado != 1 ){
                System.out.println("erro na query de acesso 1: \n");
            }
            preparedStmt.close();
        }            
        catch(SQLException e){
            System.err.println("erro na query de adicionar acesso2: \n"+e.fillInStackTrace());
        }finally{
            if(conectou)
                conexao.desconectar();
        }
    }

    @Override
    public void alterarNivelPermissão(String nomeImagem, String nomeUsuarios, int nivel) {
         ConexaoSQLite conexao= new ConexaoSQLite();
        boolean conectou = false;
        try{              
            conectou=conexao.conectar();            
            String sqlUpdate = "UPDATE ACESSO_IMAGEM SET NIVEL = ?"
                    + "WHERE LOGIN = ? AND PATH = ?";             
            
            PreparedStatement preparedStmt = conexao.criarPreparedStatement(sqlUpdate);
            if(preparedStmt==null){
                System.out.println("editar usuario nao criou o stmt");
            }
            preparedStmt.setInt(1,nivel);
            preparedStmt.setString(2,nomeUsuarios);                
            preparedStmt.setString(3,nomeImagem);                
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
    public MementoImagem salvar(String path,String folder) {
       ConcreteMemento memento= new ConcreteMemento(path,folder);
        return memento;
    }

    @Override
    public void restaurar(MementoImagem memento) {
        ConcreteMemento  snapshot=(ConcreteMemento)memento;
         ConexaoSQLite conexao= new ConexaoSQLite();
              boolean conectou = false;        
        try{
            conectou=conexao.conectar();
            String sqlInsert = "INSERT INTO IMAGEM(" +                            
                            "PATH"+
                            ") VALUES(?)"+
                             ";";
            PreparedStatement preparedStmt = conexao.criarPreparedStatement(sqlInsert);            
            preparedStmt.setString(1, snapshot.getState()[0]);
         
            int resultado = preparedStmt.executeUpdate();
            if(resultado != 1 ){
                System.out.println("erro na query restauração 1: \n");
            }
            preparedStmt.close();
            MudaVisibilidadeArquivo visibilidade=new MudaVisibilidadeArquivo(snapshot.getState()[1]+snapshot.getState()[0]);
            visibilidade.setHiddenAttribFalse();
        }            
        catch(SQLException e){
            System.err.println("erro na query de adicionar acesso2: \n"+e.fillInStackTrace());
        }finally{
            if(conectou)
                conexao.desconectar();
        }
    }
    
 
   
    
}
