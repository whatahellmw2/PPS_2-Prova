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

    public ConcreteMemento(String path) {
        this.path = path;
    }
    public String getState(){
        return path;
    }
}
