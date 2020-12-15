/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.login;


import DAO.IDAOUsuario;
import DAO.UsuarioQuerys;
import Model.usuario.FactoryUsuario;
import Model.usuario.Logado;
import Model.usuario.UsuarioGenerico;
import Presenter.principal.PrincipalPresenter;
import View.CadastroView;
import java.awt.Color;
import java.awt.event.ActionEvent;


/**
 *
 * @author Thiago
 */
public class CadastroPresenter {
    private CadastroView view;
    public CadastroPresenter() {
        this.view=new CadastroView();
        this.view.setLocationRelativeTo(null);
        this.view.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        cadastrar();
        cancelar();
        this.view.setVisible(true);
    }
    public void cadastrar(){
        this.view.getjButtonCadastrar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDAOUsuario dao = new UsuarioQuerys();
                if(view.getjTextFieldSenha().getText().equals(view.getjTextFieldConfirmarSenha().getText())){
                    FactoryUsuario factory = new Logado();
                    UsuarioGenerico usuario = factory.criarUsuario(view.getjTextFieldNomeUsuario().getText(), view.getjTextFieldSenha().getText(), "administrador");
                    dao.addUsuario(usuario.getLogin(), usuario.getSenha(), usuario.getNivel());
                    view.setVisible(false);
                    new PrincipalPresenter();  
                }else{
                    view.getjLabelErro().setForeground(Color.red);
                    view.getjLabelErro().setText("Confirme Sua Senha");
                }
                
            }
        });
    }
    public void cancelar(){
        this.view.getjButtonCancelar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("cancelado");
                view.setVisible(false);
            }
        });
    }
}
