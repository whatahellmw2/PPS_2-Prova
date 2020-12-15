/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.imagens;

import DAO.IDAOImagens;
import DAO.ImagemQuerys;
import Model.usuario.UsuarioLogado;
import View.BuscarImagensView;
import java.awt.FileDialog;
import java.awt.Window;
import javax.swing.WindowConstants;

/**
 *
 * @author Thiago
 */
public class BuscaImagensPresenter {
    private BuscarImagensView view;
    private String nomeImagem;
    public BuscaImagensPresenter() {
        this.view = new BuscarImagensView();
        this.view.getFd().setVisible(true);
        this.nomeImagem=this.view.getFd().getFile();
            
    }
    public String exibirImagem(){
        return this.nomeImagem;
    }

}
