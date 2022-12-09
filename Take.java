package control;
import java.io.*;
import java.awt.event.*;
import view.Bouton;
public class Take implements MouseListener {
    DataOutputStream output;
    public Take(DataOutputStream out){
        this.output=out;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Bouton bou=(Bouton)e.getSource();
       String file= bou.getFichier();
       try {
          output.writeBoolean(true);
          output.writeUTF(file);
          output.flush();
          System.out.println(file);
       } catch (Exception io) {
        //TODO: handle exception
       }
       
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
