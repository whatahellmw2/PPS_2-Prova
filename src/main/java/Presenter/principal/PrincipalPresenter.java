/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.principal;

import Model.usuario.UsuarioLogado;
import Presenter.imagens.BuscaImagensPresenter;
import Presenter.manterUsuarios.VisualizarUsuariosPresenter;
import View.PrincipalView;

import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Thiago
 */
public class PrincipalPresenter {
    private PrincipalView view;
    private PrincipalState state;
    public PrincipalPresenter() {
        this.view = new PrincipalView();
        prencherRodaPe();
        
        if(UsuarioLogado.getInstancia().getNivel().equals("comum")){
            this.state= new PrincipalComumState(this);
        }else{
            this.state= new PrincipalAdminState(this);
        }
        manterUsuarios();
        buscarImagens();
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
       
    }
    public void changeState(PrincipalState state){
        this.state=state;
    }
            
    public void prencherRodaPe(){
        this.view.getjLabelNome().setText(UsuarioLogado.getInstancia().getLogin());
        this.view.getjLabelNivel().setText(UsuarioLogado.getInstancia().getNivel());
    }

    public PrincipalView getView() {
        return view;
    }
    public void manterUsuarios(){
        this.view.getjMenuItemManterUsuarios().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VisualizarUsuariosPresenter();
            }
        });
        
    }
    public void buscarImagens(){
        this.view.getjMenuItemBuscarImagens().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscaImagensPresenter presenter = new BuscaImagensPresenter();
                String nomeImagem=presenter.exibirImagem();
                
                
                ImageIcon icon = new ImageIcon(".\\imagens\\comida\\"+nomeImagem);
                
                Image image = icon.getImage().getScaledInstance(view.getjLabelImagem().getWidth(), view.getjLabelImagem().getHeight(), Image.SCALE_SMOOTH);
                view.getjLabelImagem().setIcon(new ImageIcon(image));
            }
        });
    }
}
