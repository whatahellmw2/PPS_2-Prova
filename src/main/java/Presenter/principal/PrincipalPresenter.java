/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter.principal;

import DAO.IDAOImagens;
import DAO.IDAONotificacao;
import DAO.ImagemQuerys;
import DAO.MementoImagem;
import DAO.NotificacaoQuerys;
import Model.imagem.Imagem;
import Model.imagem.ProxyImage;
import Model.usuario.UsuarioLogado;
import Presenter.imagens.BuscaImagensPresenter;
import Presenter.manterUsuarios.VisualizarUsuariosPresenter;
import Presenter.notificacoes.NotificacoesPresenter;
import View.PrincipalView;
import business.MudaVisibilidadeArquivo;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago
 */
public class PrincipalPresenter {
    private PrincipalView view;
    private PrincipalState state;
    private String folder;
    private Imagem imagem;
    private Queue<MementoImagem> historico;
    //private String ImagemAtual;
    public PrincipalPresenter() {
        this.view = new PrincipalView();
        historico= new LinkedList<>();
       
        prencherRodaPe();
       
        if(UsuarioLogado.getInstancia().getNivel().equals("comum")){
            this.state= new PrincipalComumState(this);
        }else{
            this.state= new PrincipalAdminState(this);
        }
        
        obterNotificações();
        manterUsuarios();
        buscarImagens();
        compartilharImagem();
        excluirImagem();
        desfazer();
        exibirNotificacoes();
        editarConta();
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
       
    }
    
    public void changeState(PrincipalState state){
        this.state=state;
    }
            
    public void prencherRodaPe(){
        this.view.getjLabelNome().setText(UsuarioLogado.getInstancia().getLogin());
        this.view.getjLabelNivel().setText(UsuarioLogado.getInstancia().getNivel());
    }

    public PrincipalView getView() {
        return view;
    }
    
    public void manterUsuarios(){
        this.view.getjMenuItemManterUsuarios().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VisualizarUsuariosPresenter();
            }
        });
        
    }
    
    public void buscarImagens(){
        this.view.getjMenuItemBuscarImagens().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                BuscaImagensPresenter presenter = new BuscaImagensPresenter();
                String[] retorno=presenter.exibirImagem();
                String nomeImagem=retorno[1];
                folder=retorno[0];        
                
                imagem = new ProxyImage(nomeImagem, view.getjLabelImagem().getWidth(), view.getjLabelImagem().getHeight(),folder);
                
                if(imagem.visualizarImagem(view)){
                    view.getjButtonCompartilhar().setEnabled(true);
                    view.getjButtonExcluir().setEnabled(true);
                    imagem.visualizarImagem(view);
                }else{
                    view.getjButtonCompartilhar().setEnabled(false);
                    view.getjButtonExcluir().setEnabled(false);
                   Object[] options= new Object[]{"Solicitar Acesso","Cancelar"                       
                   };
                    int opcao=JOptionPane.showOptionDialog(null, "Você não tem acesso a esta Imagem", "Atenção", 0, 0, null,options , state);
                    
                    if(opcao==0){
                        IDAONotificacao dao = new NotificacaoQuerys();
                        dao.solicitarPermissao(nomeImagem,"Solicita permissão para visualizar a Imagem" );
                        JOptionPane.showMessageDialog(null, "Solicitação Enviada");
                    }
                }

            }
        });
    }
    
    public void compartilharImagem(){
        this.view.getjButtonCompartilhar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                IDAOImagens dao = new ImagemQuerys();
                System.out.println("path"+imagem.getPath());
                int nivel=dao.verificarNivelDePermissão(imagem.getPath(),UsuarioLogado.getInstancia().getLogin());
                
                if(nivel>=2||UsuarioLogado.getInstancia().getNivel().equals("administrador")){
                    CompartilharPresenter presenter = new CompartilharPresenter(imagem.getPath());
                    
                }else{
                    Object[] options= new Object[]{"Solicitar Acesso","Cancelar"};
                    int opcao=JOptionPane.showOptionDialog(null, "Você não tem permissão para compartilhar esta imagem", "Atenção", 0, 0, null,options , state);
                     if(opcao==0){
                         IDAONotificacao dao2 = new NotificacaoQuerys();
                         dao2.solicitarPermissao(imagem.getPath(),"Solicitar permissão para compartilhar a Imagem" );
                         JOptionPane.showMessageDialog(null, "Solicitação Enviada");
                    }
                }
            }
        });
    }
    
    public void excluirImagem(){
        this.view.getjButtonExcluir().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDAOImagens dao = new ImagemQuerys();
                int nivel=dao.verificarNivelDePermissão(imagem.getPath(),UsuarioLogado.getInstancia().getLogin());
                if(nivel==3||UsuarioLogado.getInstancia().getNivel().equals("administrador")){
                    dao.excluirImagem(imagem.getPath());
                    historico.add(dao.salvar(imagem.getPath(),folder));
                    view.getjButtonDesfazer().setEnabled(true);
                    MudaVisibilidadeArquivo visibilidade=new MudaVisibilidadeArquivo(folder+imagem.getPath());
                    visibilidade.setHiddenAttrib();
                    view.getjLabelImagem().setIcon(null);
                    JOptionPane.showMessageDialog(null, "Imagem Excluída");
                }else{
                    Object[] options= new Object[]{"Solicitar Acesso","Cancelar"};
                    int opcao=JOptionPane.showOptionDialog(null, "Você não tem permissão para excluir esta imagem", "Atenção", 0, 0, null,options , state);
                     if(opcao==0){
                        IDAONotificacao dao2 = new NotificacaoQuerys();
                        dao2.solicitarPermissao(imagem.getPath(),"Solicita permissão para excluir a Imagem" );
                        JOptionPane.showMessageDialog(null, "Solicitação Enviada");
                    }
                    
                }
            }
        });
    }
    
    public void desfazer(){
        this.view.getjButtonDesfazer().addActionListener( new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDAOImagens dao = new ImagemQuerys();
                dao.restaurar(historico.poll());
                if(historico.isEmpty())
                    view.getjButtonDesfazer().setEnabled(false);
                JOptionPane.showMessageDialog(null, "imagem restaurada");
            }
        });
    }
    
    public void exibirNotificacoes(){
        this.view.getjButtonNotificacoes().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(UsuarioLogado.getInstancia().getNivel().equals("comum")){      
                    NotificacoesPresenter presenter = new NotificacoesPresenter(false);
                }else{
                    NotificacoesPresenter presenter = new NotificacoesPresenter(true);                    
                }
                
            }
        });
        
    }
    
    public void obterNotificações(){
        IDAONotificacao dao = new NotificacaoQuerys();        
        if(dao.obterNotificacoes().size()>0){
            Color color = new Color(219, 112, 31);
            this.view.getjButtonNotificacoes().setBackground(color);
            this.view.getjButtonNotificacoes().setText("Notificações("+dao.obterNotificacoes().size()+")");
        }else{
            Color color = new Color(171,202,219);
            this.view.getjButtonNotificacoes().setBackground(color);
            this.view.getjButtonNotificacoes().setText("Notificações");
        }
        
        this.view.getjButtonNotificacoes().revalidate();
        this.view.update(this.view.getjButtonNotificacoes().getGraphics());
    }
    
    public void editarConta(){
        this.view.getjMenuItemConta().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContaPresenter presenter = new ContaPresenter();
            }
        });
        
       
    }
}
