/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.notificacoes;

import DAO.IDAONotificacao;
import DAO.NotificacaoQuerys;
import Model.models.Notificacao;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Thiago
 */
public abstract class NotificacoesState {
    protected NotificacoesPresenter presenter;

    public NotificacoesState(NotificacoesPresenter presenter) {
        this.presenter = presenter;
        preencherNotificacoes();
        
    }
    public void mudarCorDaTabela(){
        int row2= this.presenter.getView().getjTableNotificacoes().getSelectedRow();
        
        this.presenter.getView().getjTableNotificacoes().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
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
    public void preencherNotificacoes(){
         Color color = new Color(56, 171, 234,50);  
            this.presenter.getView().getjTableNotificacoes().setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setBackground(color);
                return component;
            }
         });
        
        IDAONotificacao dao = new NotificacaoQuerys();
        ArrayList<Notificacao> notificacoes = dao.obterNotificacoes();
        DefaultTableModel m = (DefaultTableModel) this.presenter.getView().getjTableNotificacoes().getModel();
        m.setNumRows(0);   
        for(Notificacao notificao:notificacoes){
            m.addRow(new Object[]{
                notificao.getDescricao(),
                notificao.getPath(),
                notificao.getRementente()
            });
        }        
    }
    public abstract void configurarTela();
    public abstract void lerNotificacao();
}
