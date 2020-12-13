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
public abstract class PrincipalState {
    protected PrincipalPresenter presenter;
    public PrincipalState(PrincipalPresenter presenter){
        this.presenter=presenter;
        configurarView();
    }
    public abstract void configurarView();
}
