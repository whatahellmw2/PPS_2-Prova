/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.manterUsuarios;

import DAO.IDAOUsuario;
import DAO.UsuarioQuerys;
import Model.usuario.UsuarioGenerico;
import View.manterUsuarios.ManterUsuariosView;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thiago
 */
public class VisualizarUsuariosPresenter {
    private ManterUsuariosView view;

    public VisualizarUsuariosPresenter() {
    this.view=new ManterUsuariosView();
    preencherTabela();
    novo();
    editar();
    excluir();
    this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.view.setLocationRelativeTo(null);
    this.view.setVisible(true);
    }
    public void preencherTabela(){
        DefaultTableModel model= (DefaultTableModel)this.view.getjTableUsuarios().getModel();
        model.setNumRows(0);
        IDAOUsuario dao = new UsuarioQuerys();
        ArrayList<UsuarioGenerico> usuarios = dao.buscarUsuarios();
        for(UsuarioGenerico usuario: usuarios){
            model.addRow(new Object[]{usuario.getLogin(),usuario.getNivel()
            });
        }
    }
    public void novo(){
        this.view.getjButtonNovo().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManterUsuariosPresenter presenter= new ManterUsuariosPresenter();
                presenter.changeState(new ManterUsuariosCadastrarState(presenter));
                 
            }
        });
    }
    public void editar(){
        this.view.getjButtonEditar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManterUsuariosPresenter presenter= new ManterUsuariosPresenter();
                ManterUsuariosEditarSatate state= new ManterUsuariosEditarSatate(presenter);
                String login=(String)view.getjTableUsuarios().getValueAt(view.getjTableUsuarios().getSelectedRow(), 0);
                String nivel=(String)view.getjTableUsuarios().getValueAt(view.getjTableUsuarios().getSelectedRow(), 1);
                state.configurarTela(login,nivel);
                presenter.changeState(state);
                
                 
            }
        });
    }
    public void excluir(){
        this.view.getjButtonExcluir().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManterUsuariosPresenter presenter= new ManterUsuariosPresenter();
                ManterUsuariosExcluirState state= new ManterUsuariosExcluirState(presenter);              
                String login=(String)view.getjTableUsuarios().getValueAt(view.getjTableUsuarios().getSelectedRow(), 0);
                String nivel=(String)view.getjTableUsuarios().getValueAt(view.getjTableUsuarios().getSelectedRow(), 1);
                state.configurarTela(login,nivel);
                presenter.changeState(state);
            }
        });
    }
    
}
