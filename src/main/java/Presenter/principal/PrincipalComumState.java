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
public class PrincipalComumState extends PrincipalState{

    public PrincipalComumState(PrincipalPresenter presenter) {
        super(presenter);
    }

    @Override
    public void configurarView() {
        this.presenter.getView().getjMenuUsuarios().setEnabled(false);
    }

    
    
}
