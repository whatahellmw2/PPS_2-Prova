/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.principal;

/**
 *
 * @author Thiago
 */
public class PrincipalAdminState extends PrincipalState {
    
    public PrincipalAdminState(PrincipalPresenter presenter) {
        super(presenter);       
    }
    @Override
    public void configurarView(){
        this.presenter.getView().getjMenuUsuarios().setEnabled(true);       
    }
}
