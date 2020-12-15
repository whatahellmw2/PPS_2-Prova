/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.notificacoes;

import DAO.IDAONotificacao;
import DAO.NotificacaoQuerys;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Thiago
 */
public class NotificacoesComumState extends NotificacoesState{
    
    public NotificacoesComumState(NotificacoesPresenter presenter) {
        super(presenter);
    }

    @Override
    public void lerNotificacao() {
                int row2= this.presenter.getView().getjTableNotificacoes().getSelectedRow();
        
        this.presenter.getView().getjTableNotificacoes().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                    int row=presenter.getView().getjTableNotificacoes().getSelectedRow();
                    IDAONotificacao dao = new NotificacaoQuerys();
                    dao.lerNotificacao((String)presenter.getView().getjTableNotificacoes().getValueAt(row, 0),
                (String)presenter.getView().getjTableNotificacoes().getValueAt(row, 2), (String)presenter.getView().getjTableNotificacoes().getValueAt(row, 1));    
                    Color color = new Color(216, 229, 230);                     

                    presenter.getView().getjTableNotificacoes().setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table,
                            Object value, boolean isSelected, boolean hasFocus,
                            int row, int column) {
                        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        component.setBackground(color);
                        return component;
                    }
                 });
                
            }
        });
       
    }

    @Override
    public void configurarTela() {
        this.presenter.getView().getjButtonConceder().setVisible(false);
        this.presenter.getView().getjButtonNegar().setVisible(false);
        this.presenter.getView().getjRadioButtonCompartilhar().setVisible(false);
        this.presenter.getView().getjRadioButtonExcluir().setVisible(false);
        this.presenter.getView().getjRadioButtonVisualizar().setVisible(false);
        this.presenter.getView().getjLabelInformativo().setVisible(false);
        lerNotificacao();
    }
    
}
