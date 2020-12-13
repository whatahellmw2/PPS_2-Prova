/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.manterUsuarios;

/**
 *
 * @author Thiago
 */
public abstract class ManterUsuariosState {
    protected ManterUsuariosPresenter presenter;
    
    public ManterUsuariosState(ManterUsuariosPresenter presenter){
        this.presenter=presenter;
    }
    public void setPresenter(ManterUsuariosPresenter presenter){
        this.presenter=presenter;
    }
    public abstract void confirmar();
}
