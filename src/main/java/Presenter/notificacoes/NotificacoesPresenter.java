/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.notificacoes;

import View.NotificacoesView;
import javax.swing.WindowConstants;

/**
 *
 * @author Thiago
 */
public class NotificacoesPresenter {
    private NotificacoesView view;
    private NotificacoesState state;
    public NotificacoesPresenter(boolean stateAdmin) {
        this.view = new NotificacoesView();
        if(stateAdmin){
            this.state=new NotificacoesAdminState(this);
        }else{
            this.state=new NotificacoesComumState(this);
        }
        
        this.state.configurarTela();
        this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
    }
    public void iniciarView(){
       
    }
    public void changeState(NotificacoesState state){
        this.state=state; 
    }
    
    public NotificacoesView getView() {
        return view;
    }
    
}
