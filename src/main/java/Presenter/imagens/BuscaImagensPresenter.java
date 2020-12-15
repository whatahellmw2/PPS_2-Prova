/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.imagens;

import View.BuscarImagensView;

/**
 *
 * @author Thiago
 */
public class BuscaImagensPresenter {
    private BuscarImagensView view;
    private String nomeImagem;
    private String dir;
    public BuscaImagensPresenter() {
        this.view = new BuscarImagensView();
        this.view.getFd().setVisible(true);
        this.nomeImagem=this.view.getFd().getFile();
        this.dir=this.view.getFd().getDirectory();
        System.out.println("diretorio"+this.dir);
            
    }
    public String[] exibirImagem(){
        String[] path={this.dir,this.nomeImagem};
        //return this.nomeImagem;
        return path;
    }

}
