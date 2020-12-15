/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.usuario.UsuarioGenerico;
import java.util.ArrayList;

/**
 *
 * @author Thiago
 */
public interface IDAOUsuario {
      public void addUsuario(String login, String senha, String nivel);
      public UsuarioGenerico efetuarLogin(String login, String senha );
      public boolean isEmpty();
      public  ArrayList<UsuarioGenerico> buscarUsuarios();
      public void editarUsuario(String login, String nivel);
      public void excluirUsuario(String login);
      public ArrayList<UsuarioGenerico> buscarUsuariosPorNivel(String nivel);
     
}
