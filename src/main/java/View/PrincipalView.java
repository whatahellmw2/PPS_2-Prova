/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Thiago
 */
public class PrincipalView extends javax.swing.JFrame {

    /**
     * Creates new form tetse
     */
    public PrincipalView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonCompartilhar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jLabelImagem = new javax.swing.JLabel();
        jLabelImagem.setBorder(new BevelBorder(BevelBorder.LOWERED));
        jPanelRodaPe = new javax.swing.JPanel();
        jPanelRodaPe.setBorder(new BevelBorder(BevelBorder.LOWERED));
        jLabelNome = new javax.swing.JLabel();
        jLabelNivel = new javax.swing.JLabel();
        jButtonNotificacoes = new JGradientButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuImagens = new javax.swing.JMenu();
        jMenuItemBuscarImagens = new javax.swing.JMenuItem();
        jMenuUsuarios = new javax.swing.JMenu();
        jMenuItemManterUsuarios = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuConta = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonCompartilhar.setText("Compartilhar");
        jButtonCompartilhar.setEnabled(false);

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.setEnabled(false);

        jLabelImagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButtonNotificacoes.setBackground(new java.awt.Color(171, 202, 219));
        jButtonNotificacoes.setText("Notificações");

        javax.swing.GroupLayout jPanelRodaPeLayout = new javax.swing.GroupLayout(jPanelRodaPe);
        jPanelRodaPe.setLayout(jPanelRodaPeLayout);
        jPanelRodaPeLayout.setHorizontalGroup(
            jPanelRodaPeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRodaPeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNome)
                .addGap(141, 141, 141)
                .addComponent(jLabelNivel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonNotificacoes)
                .addGap(86, 86, 86))
        );
        jPanelRodaPeLayout.setVerticalGroup(
            jPanelRodaPeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRodaPeLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanelRodaPeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jLabelNivel)
                    .addComponent(jButtonNotificacoes))
                .addContainerGap())
        );

        jMenuImagens.setText("Imagens");

        jMenuItemBuscarImagens.setText("Buscar");
        jMenuImagens.add(jMenuItemBuscarImagens);

        jMenuBar1.add(jMenuImagens);

        jMenuUsuarios.setText("Usuários");

        jMenuItemManterUsuarios.setText("Manter Usuários");
        jMenuUsuarios.add(jMenuItemManterUsuarios);

        jMenuItem2.setText("jMenuItem2");
        jMenuUsuarios.add(jMenuItem2);

        jMenuBar1.add(jMenuUsuarios);

        jMenuConta.setText("Conta");
        jMenuBar1.add(jMenuConta);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelRodaPe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCompartilhar)
                        .addGap(27, 27, 27)
                        .addComponent(jButtonExcluir))
                    .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCompartilhar)
                    .addComponent(jButtonExcluir))
                .addGap(28, 28, 28)
                .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelRodaPe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 public JMenu getjMenuConta() {
        return jMenuConta;
    }

    public JMenu getjMenuImagens() {
        return jMenuImagens;
    }

    public JMenu getjMenuUsuarios() {
        return jMenuUsuarios;
    }

  private class JGradientButton extends JButton{
    private JGradientButton(){
        super();
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0), 
                getBackground(), 
                new Point(0, getHeight()/3), 
                Color.WHITE));
        g2.fillRect(0, 0, getWidth(), getHeight()/3);
        g2.setPaint(new GradientPaint(
                new Point(0, getHeight()/3), 
                Color.WHITE, 
                new Point(0, getHeight()), 
                getBackground()));
        g2.fillRect(0, getHeight()/3, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }
}

    public JButton getjButtonNotificacoes() {
        return jButtonNotificacoes;
    }

    public JLabel getjLabelNivel() {
        return jLabelNivel;
    }

    public JLabel getjLabelNome() {
        return jLabelNome;
    }

    public JMenuItem getjMenuItemManterUsuarios() {
        return jMenuItemManterUsuarios;
    }

    public JMenuItem getjMenuItemBuscarImagens() {
        return jMenuItemBuscarImagens;
    }

    public JLabel getjLabelImagem() {
        return jLabelImagem;
    }

    public JButton getjButtonCompartilhar() {
        return jButtonCompartilhar;
    }

    public JButton getjButtonExcluir() {
        return jButtonExcluir;
    }

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCompartilhar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNotificacoes;
    private javax.swing.JLabel jLabelImagem;
    private javax.swing.JLabel jLabelNivel;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuConta;
    private javax.swing.JMenu jMenuImagens;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemBuscarImagens;
    private javax.swing.JMenuItem jMenuItemManterUsuarios;
    private javax.swing.JMenu jMenuUsuarios;
    private javax.swing.JPanel jPanelRodaPe;
    // End of variables declaration//GEN-END:variables
}
