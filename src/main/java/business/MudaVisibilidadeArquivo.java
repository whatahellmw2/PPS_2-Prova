/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;

/**
 *
 * @author Thiago
 */
public class MudaVisibilidadeArquivo {
    private Path filePath;
  public MudaVisibilidadeArquivo(String path) {
     filePath = Paths.get(path);
    
  }
  
  public  void setHiddenAttrib() {		
    try {
      DosFileAttributes attr = Files.readAttributes(filePath, DosFileAttributes.class);
      System.out.println(filePath.getFileName() + " Hidden attribute is " + attr.isHidden());
      Files.setAttribute(filePath, "dos:hidden", true);
      attr = Files.readAttributes(filePath, DosFileAttributes.class);
      System.out.println(filePath.getFileName() + " Hidden attribute is " + attr.isHidden());
    } catch (IOException e) {
      
      e.printStackTrace();
    } 
  }
    public void setHiddenAttribFalse() {		
    try {
      DosFileAttributes attr = Files.readAttributes(filePath, DosFileAttributes.class);
      System.out.println(filePath.getFileName() + " Hidden attribute is " + attr.isHidden());
      Files.setAttribute(filePath, "dos:hidden", false);
      attr = Files.readAttributes(filePath, DosFileAttributes.class);
      System.out.println(filePath.getFileName() + " Hidden attribute is " + attr.isHidden());
    } catch (IOException e) {
      
      e.printStackTrace();
    } 
  }

}
