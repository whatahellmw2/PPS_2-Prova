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
public interface IDAOImagens {
    public boolean verificarAcesso(String nomeImagem);
    public int verificarNivelDePermissão(String nomeImagem,String nomeUsuario);
    public void adicionarAcesso(String nomeImagem,String nomeUsuario);
    public void excluirImagem(String nomeImagem);
    public void adicionarPermissão(String nomeImagem,String nomeUsuarios, int nivel);
    public void alterarNivelPermissão(String nomeImagem,String nomeUsuarios, int nivel);
}
