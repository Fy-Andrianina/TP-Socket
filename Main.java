package server;
import javax.swing.JOptionPane;
public class Main{
    public static void main(String[] args){
        String portString=JOptionPane.showInputDialog("Enter the port: ");
        int port=Integer.valueOf(portString);
        Server servprincipale=new Server(port);

    }
}