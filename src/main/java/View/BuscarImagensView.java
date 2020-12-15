/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import business.Teste;
import java.awt.FileDialog;
import java.awt.Window;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Thiago
 */
public class BuscarImagensView {
    FileDialog fd;
    public BuscarImagensView() {
            JFrame frame = new javax.swing.JFrame();
            
            fd = new FileDialog(frame, "Escolha uma Imagem", FileDialog.LOAD);
            
            
            fd.setDirectory(".\\imagens\\comida");           
            fd.setMultipleMode(false);
            
            //FileNameExtensionFilter filter = new FileNameExtensionFilter("imagens", "jpg","png")
            
           FilenameFilter filtro = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {                    
                    return (name.endsWith(".jpg")|| name.endsWith(".png") &
                            (dir.equals(".\\imagens\\comida")|| dir.equals(".\\imagens\\pessoas")||dir.equals(".\\imagens\\animais")));
                }
            };
            
            Teste teste = new Teste();
            fd.setFilenameFilter(filtro);
            fd.getFilenameFilter();
            fd.revalidate();           
            fd.update(fd.getGraphics());            
            frame.revalidate();
            frame.update(frame.getGraphics());
            
            

    }

    public FileDialog getFd() {
        return fd;
    }
        
}
