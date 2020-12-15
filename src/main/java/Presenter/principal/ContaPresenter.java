/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.principal;

import DAO.IDAOUsuario;
import DAO.UsuarioQuerys;
import View.ContaView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Thiago
 */
public class ContaPresenter {
    private ContaView view;

    public ContaPresenter() {
        this.view=new ContaView();
        this.view.setLocationRelativeTo(null);
        this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mudarSenha();
        this.view.setVisible(true);
                
    }
    public void mudarSenha(){
        this.view.getjButtonConfirmar().addActionListener( new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDAOUsuario dao = new UsuarioQuerys();
                dao.mudarSenha(view.getjTextFieldSenha().getText());
                JOptionPane.showMessageDialog(null, "senha altera com sucesso");
            }
        });
    }
    
}
