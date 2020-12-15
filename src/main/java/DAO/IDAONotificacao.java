/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.models.Notificacao;
import java.util.ArrayList;

/**
 *
 * @author Thiago
 */
public interface IDAONotificacao {
    public void solicitarPermissao(String Imagem,String Nivel);
    public void notificarCompartilhamento(String Imagem, String login);
    public ArrayList<Notificacao> obterNotificacoes();
    public void lerNotificacao(String descricao, String remetente, String path );
     public void excluirNotificacaoRedundante(String descricao, String remetente, String path);
}
