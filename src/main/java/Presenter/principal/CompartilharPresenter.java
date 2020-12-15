/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.principal;

import DAO.IDAOImagens;
import DAO.IDAONotificacao;
import DAO.IDAOUsuario;
import DAO.ImagemQuerys;
import DAO.NotificacaoQuerys;
import DAO.UsuarioQuerys;
import Model.usuario.UsuarioGenerico;
import View.CompartilharImagemView;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;
/**
 *
 * @author Thiago
 */
public class CompartilharPresenter {
    private CompartilharImagemView view;

    public CompartilharPresenter(String nomeImagem) {
        this.view = new CompartilharImagemView();
        this.view.setLocationRelativeTo(null);
        this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        preencherCompartilhar();
        compartilharImagem(nomeImagem);
        this.view.setVisible(true);
    }
    public void preencherCompartilhar(){
        ArrayList<UsuarioGenerico> usuarios;
        IDAOUsuario dao = new UsuarioQuerys();
        usuarios=dao.buscarUsuariosPorNivel("comum");
        DefaultListModel m = (DefaultListModel) this.view.getjListUsuarios().getModel();
        for(UsuarioGenerico usuario: usuarios){
          m.addElement(usuario.getLogin());           
        }
        
    }
    public void compartilharImagem(String nomeImagem){
        this.view.getjButtonCompartilhar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               DefaultListModel m = (DefaultListModel) view.getjListUsuarios().getModel();
               ArrayList<String> usuarios=(ArrayList<String>)view.getjListUsuarios().getSelectedValuesList();
               IDAOImagens dao = new ImagemQuerys();
                IDAONotificacao dao2 = new NotificacaoQuerys();
               for(String s:usuarios){
                   dao.adicionarAcesso(nomeImagem, s);
                   dao2.notificarCompartilhamento(nomeImagem, s);
               }
               JOptionPane.showMessageDialog(null, "Compartilhamento Concluído");
               view.dispose();
            }
        });
    }
    
}
