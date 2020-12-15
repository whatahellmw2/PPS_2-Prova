/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.models;

/**
 *
 * @author Thiago
 */
public class Notificacao {
    private String descricao;
    private String rementente;
    private String destinatario;
    private String path;
    boolean status;

    public Notificacao(String descricao, String rementente, String path) {
        this.descricao = descricao;
        this.rementente = rementente;
        this.path = path;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRementente() {
        return rementente;
    }

    public void setRementente(String rementente) {
        this.rementente = rementente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
