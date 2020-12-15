/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.imagem;

import DAO.IDAOImagens;

import DAO.ImagemQuerys;

import Model.usuario.UsuarioLogado;
import View.PrincipalView;
import java.awt.Image;
import javax.swing.ImageIcon;


/**
 *
 * @author Thiago
 */
public class ProxyImage implements Imagem{
    private String path;
    private    Image image;
    private ReallImage realImagem;
    int height,width;
    private String folder;

    @Override
    public String getPath() {
        return path;
    }
    
    public ProxyImage(String path, int height, int width, String folder) {
        this.path = path;
        this.height=height;
        this.width=width;
        this.folder=folder;
    }
    
    @Override
    public boolean visualizarImagem(PrincipalView view) {
        if(this.realImagem==null){
            if(verificarAcesso()){                    
                    ImageIcon icon = new ImageIcon(folder+path);                
                    image = icon.getImage().getScaledInstance(height, width, Image.SCALE_SMOOTH);                    
                    this.realImagem = new ReallImage(this.path, image);
                    //realImagem.visualizarImagem(view);
                    return true;
                }else{
                    return false;
                }
            
        }
        realImagem.visualizarImagem(view);
        return true;
    }
        public boolean verificarAcesso(){
        if(UsuarioLogado.getInstancia().getNivel().equals("comum")){
            IDAOImagens dao = new ImagemQuerys();
            if(dao.verificarAcesso(this.path)){                
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }
    }
    
}
