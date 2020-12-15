/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.imagem;


import View.PrincipalView;
import java.awt.Image;
import javax.swing.ImageIcon;


/**
 *
 * @author Thiago
 */
public class ReallImage implements Imagem{
    private String path;
       private Image image;
    @Override
    public String getPath() {
        return path;
    }
    public ReallImage(String path, Image image) {
        this.path = path;
        this.image=image;
    }
    @Override
    public boolean visualizarImagem(PrincipalView view) {
         view.getjLabelImagem().setIcon(new ImageIcon(image));
         return true;
    }
    
}
