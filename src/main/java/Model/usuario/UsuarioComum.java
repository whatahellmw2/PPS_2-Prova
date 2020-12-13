/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.usuario;

/**
 *
 * @author Thiago
 */
public class UsuarioComum extends UsuarioGenerico{
    public UsuarioComum(String login, String senha){
        this.login=login;
        this.senha=senha;
        this.nivel="comum";
    } 
}
