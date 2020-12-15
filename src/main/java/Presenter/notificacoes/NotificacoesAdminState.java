/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.notificacoes;

import DAO.IDAOImagens;
import DAO.IDAONotificacao;
import DAO.ImagemQuerys;
import DAO.NotificacaoQuerys;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago
 */
public class NotificacoesAdminState extends NotificacoesState{
    
    public NotificacoesAdminState(NotificacoesPresenter presenter) {
        super(presenter);
    }

    @Override
    public void lerNotificacao() {
            int row2= this.presenter.getView().getjTableNotificacoes().getSelectedRow();
            this.presenter.getView().getjButtonConceder().addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   
                int row=presenter.getView().getjTableNotificacoes().getSelectedRow();    
                String descricao= (String)presenter.getView().getjTableNotificacoes().getValueAt(row, 0)
                            ,remetente=(String)presenter.getView().getjTableNotificacoes().getValueAt(row, 2)
                            ,path=(String)presenter.getView().getjTableNotificacoes().getValueAt(row, 1);
                    
                    IDAONotificacao dao = new NotificacaoQuerys();
                    dao.lerNotificacao(descricao,remetente,path);
                    
                    IDAOImagens daoI= new ImagemQuerys();
                    int nivel=Integer.parseInt(presenter.getView().getButtonGroup1().getSelection().getActionCommand());
                    if( daoI.verificarNivelDePermissão(path, remetente)==-1){
                        daoI.adicionarPermissão(path, remetente,nivel );
                    }else {
                         daoI.alterarNivelPermissão(path, remetente, nivel);
                    }
                    JOptionPane.showMessageDialog(null, "Permissao Concedida");
                    dao.excluirNotificacaoRedundante(descricao, remetente, path);
                   preencherNotificacoes();
                   
                
                }
            });
           
                
            
        
    }

    @Override
    public void configurarTela() {
        lerNotificacao();
    }
    
}
