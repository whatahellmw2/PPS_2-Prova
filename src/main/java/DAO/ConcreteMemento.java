/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Thiago
 */
public class ConcreteMemento implements MementoImagem{
   private String path;
   private String folder;
    public ConcreteMemento(String path, String folder) {
        this.path = path;
        this.folder=folder;
    }
    public String[] getState(){
        return new String[]{path,folder};
    }
}
