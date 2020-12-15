/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.imagem;


import View.PrincipalView;

/**
 *
 * @author Thiago
 */
public interface Imagem {
   public boolean visualizarImagem(PrincipalView view);
   public String getPath();
}
