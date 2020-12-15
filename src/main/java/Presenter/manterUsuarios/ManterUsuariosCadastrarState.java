/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.manterUsuarios;

import DAO.IDAOUsuario;
import DAO.UsuarioQuerys;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago
 */
public class ManterUsuariosCadastrarState extends ManterUsuariosState{
        
        public  ManterUsuariosCadastrarState(ManterUsuariosPresenter presenter){
            super(presenter);
        }
  
    @Override
    public void confirmar() {
        IDAOUsuario dao = new UsuarioQuerys();
        dao.addUsuario(this.presenter.getView().getjTextFieldLogin().getText(), 
                "",
                this.presenter.getView().getButtonGroup2().getSelection().getActionCommand());
        //System.out.println("Cadastrando"+this.presenter.getView().getButtonGroup2().getSelection().getActionCommand());
        
        JOptionPane.showMessageDialog(null, "Usuario Cadastrado");
        this.presenter.getView().dispose();
    }
    
}
