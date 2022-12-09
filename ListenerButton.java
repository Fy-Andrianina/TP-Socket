package control;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import javax.swing.filechooser.FileSystemView;
import client.Client;
import view.PanelBiblio;

public class ListenerButton implements ActionListener {
    JPanel pan;
    Client client;
    public JPanel getPan() {
        return pan;
    }
    public void setPan(JPanel pan) {
        this.pan = pan;
    }
    public ListenerButton(JPanel pan,Client client){
        this.pan=pan;
        this.client=client;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton bou=(JButton)e.getSource();
      if(e.getActionCommand()=="Envoyer un fichier"){
        JFileChooser choose=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            choose.setDialogTitle("Choisissez un fichier: ");
           // System.out.println("Envoyer un fichier");

            int res=choose.showOpenDialog(null);
            File filechoosed=null;
             if(res==JFileChooser.APPROVE_OPTION){
                filechoosed=choose.getSelectedFile();
             
                int res2=JOptionPane.showConfirmDialog(this.pan,"Voulez vous envoyer le fichier "+filechoosed.getName()+"?","Confirmation",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(res2==0){
                    try {
                        String filename = filechoosed.getName();
                        byte[] fileNameBytes = filename.getBytes();
                        this.client.getOut().writeInt(filename.length());
                        this.client.getOut().write(fileNameBytes);
                       // System.out.println(new String(fileNameBytes));
                       // System.out.println(filename.length()+" namelong");
                        
                        byte[] b=Files.readAllBytes(filechoosed.toPath()); //conversion du fichier em tableau de bytes
                        this.client.getOut().writeInt((int)filechoosed.length());
                        this.client.getOut().write(b);
                        this.client.getOut().flush();
                        
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
               
                }
                
             }

      }else if(e.getActionCommand()=="Prendre un fichier"){
        try {
          
            PanelBiblio biblio=new PanelBiblio(this.client.getOut());
           
        } catch (Exception io) {
            io.printStackTrace();
        }
          
        }
        
    }
    
}
