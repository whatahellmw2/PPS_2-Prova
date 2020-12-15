/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.login;

import DAO.IDAOUsuario;
import DAO.UsuarioQuerys;
import Model.usuario.Administrador;
import Model.usuario.FactoryUsuario;
import Model.usuario.Logado;
import Model.usuario.UsuarioGenerico;
import Presenter.principal.PrincipalPresenter;
import View.LoginView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago
 */
public class LoginPresenter {
    private LoginView view;

    public LoginPresenter() {
        this.view=new LoginView();
        this.view.setLocationRelativeTo(null);
        efetuarLogin();
        this.view.setVisible(true);
    }
    public void efetuarLogin(){
        this.view.getjButtonAcessar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDAOUsuario dao = new UsuarioQuerys();
                if(dao.isEmpty()){
//                    FactoryUsuario factory = new Logado();
//                    UsuarioGenerico usuario = factory.criarUsuario(view.getjTextFieldNomeUsuario().getText(), view.getjTextFieldSenha().getText(), "administrador");
//                    dao.addUsuario(usuario.getLogin(), usuario.getSenha(), usuario.getNivel());
                      
                      CadastroPresenter presenter = new CadastroPresenter();
                      new PrincipalPresenter();                      
                }else{
                    UsuarioGenerico usuario=dao.efetuarLogin(view.getjTextFieldNomeUsuario().getText(), view.getjTextFieldSenha().getText());
                    if(usuario!=null){
                        new PrincipalPresenter();
                    }else{
                         JOptionPane.showMessageDialog(null,"login ou senha incorretos");
                    }
                }
                
            }
        });
    }
}
