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
public class UsuarioLogado extends UsuarioGenerico{
    private static UsuarioLogado usuario;
    private  UsuarioLogado(String login, String senha, String nivel) {
        this.login=login;
        this.senha=senha;
        this.nivel=nivel;
    }
    public static UsuarioLogado criaInstancia(String login, String senha, String nivel){
        if(usuario==null){
            usuario = new UsuarioLogado(login,senha,nivel);
        }
        return usuario;
    }
    public static UsuarioLogado getInstancia(){        
        return usuario;
    }
}
