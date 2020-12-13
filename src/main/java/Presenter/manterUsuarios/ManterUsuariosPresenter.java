/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.manterUsuarios;

import View.manterUsuarios.ManterUsuariosStateView;
import java.awt.event.ActionEvent;
import javax.swing.WindowConstants;

/**
 *
 * @author Thiago
 */
public class ManterUsuariosPresenter {
    private ManterUsuariosStateView view;
    private ManterUsuariosState state;
    
    public ManterUsuariosPresenter(){       
        this.view=new ManterUsuariosStateView();
        confirmar();
        this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
    }
    public void changeState(ManterUsuariosState state){
        this.state=state;
    }
    public void confirmar(){
        this.view.getjButtonConfirmar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.confirmar();
            }
        });
    }

    public ManterUsuariosStateView getView() {
        return view;
    }
    
}
