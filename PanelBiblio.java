package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.Bouton;
import java.io.*;
import java.awt.*;
import control.Take;

public class PanelBiblio extends JPanel{
    public PanelBiblio(DataOutputStream out){
        JFrame frame=new JFrame("Bibliotheque");
        File rep=new File("E://lecons S3//Reseaux java//New one//backup//rep0");
        frame.setSize(new Dimension(500,300));
        frame.setFocusableWindowState(false);
        Take take=new Take(out);
        if(rep.isDirectory()){
            File[] files=rep.listFiles();
            for(int i=0;i<files.length;i++){
                Bouton bouton=new Bouton(files[i].getName());
                bouton.setFichier(files[i].getName());
                bouton.addMouseListener(take);
                   this.add(bouton);
                  
            }
            
        }
       
        frame.add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}

