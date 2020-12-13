/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.manterUsuarios;

import DAO.IDAOUsuario;
import DAO.UsuarioQuerys;

/**
 *
 * @author Thiago
 */
public class ManterUsuariosExcluirState extends ManterUsuariosState{
    String nivel,login;
    public ManterUsuariosExcluirState(ManterUsuariosPresenter presenter) {
        super(presenter);
    }
     public void configurarTela(String login, String nivel){
        this.login=login;
        this.nivel=nivel;
        this.presenter.getView().getjTextFieldLogin().setText(login);
        this.presenter.getView().getjRadioButton1().setEnabled(false);
        this.presenter.getView().getjRadioButton2().setEnabled(false);
        if(nivel.equals("comum")){
            this.presenter.getView().getButtonGroup2().setSelected(this.presenter.getView().getjRadioButton2().getModel(), true);
        }else{
            this.presenter.getView().getButtonGroup2().setSelected(this.presenter.getView().getjRadioButton1().getModel(), true);
        }
        
        this.presenter.getView().getjTextFieldLogin().setEditable(false);
    }
    @Override
    public void confirmar() {
        IDAOUsuario dao = new UsuarioQuerys();
        dao.excluirUsuario(this.login);
    }
    
}
