/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.FileDialog;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Thiago
 */
public class BuscarImagensView {
    FileDialog fd;
    public BuscarImagensView() {
            fd = new FileDialog(new javax.swing.JFrame(), "Escolha uma Imagem", FileDialog.LOAD);
            fd.setDirectory(".\\imagens\\comida");           
            fd.setMultipleMode(false);
            
            //FileNameExtensionFilter filter = new FileNameExtensionFilter("imagens", "jpg","png")
            fd.setFilenameFilter(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {                    
                    return name.endsWith(".jpg")|| name.endsWith(".png");
                }
            });
            
           
    }

    public FileDialog getFd() {
        return fd;
    }
        
}
