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
public class Logado extends FactoryUsuario {

    @Override
    public UsuarioGenerico criarUsuario(String login, String senha, String nivel) {
        this.usuario= UsuarioLogado.criaInstancia(login, senha, nivel);
        return usuario;
    }
    
}
