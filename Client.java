package client;
import javax.swing.*;
import java.awt.*;;
import java.net.*;
import java.io.*;
import client.ClientManager;
import control.ListenerButton;

public class Client{
    Socket socketClient;
    DataInputStream in;
    DataOutputStream out;
  
    public Client(String host,int port){
        try{
            this.socketClient=new Socket(host,port);
            ObjectOutputStream p=new ObjectOutputStream(this.socketClient.getOutputStream());
            p.writeObject("client");
            p.flush();

            this.in = new DataInputStream(this.socketClient.getInputStream());
            this.out = new DataOutputStream(this.socketClient.getOutputStream());
            
            
            start();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
     public static void main(String[] args){
         String Ip_addr = JOptionPane
        .showInputDialog("Enter the IP number of the server to connect: ");
        String IP = Ip_addr;

        String port = JOptionPane
        .showInputDialog("Enter the PORT number of the server to connect: ");
        int realport=Integer.valueOf(port);
        //System.out.println(realport+" "+IP);
           
        Client client=new Client(IP,realport);
             
    }
     

    public void start(){
        JFrame frame=new JFrame("Cote client");
        frame.setSize(new Dimension(500,300));
        frame.setFocusableWindowState(false);

        JButton bou=new JButton("Envoyer un fichier");
        JButton bou2=new JButton("Prendre un fichier");
        JPanel panBouton=new JPanel();
         panBouton.add(bou);
         panBouton.add(bou2);
         
         ListenerButton list=new ListenerButton(panBouton,this);
         bou.addActionListener(list);
         bou2.addActionListener(list);
        frame.add(panBouton);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public Socket getSocketClient() {
        return socketClient;
    }
    public void setSocketClient(Socket socketClient) {
        this.socketClient = socketClient;
    }
    public DataInputStream getIn() {
        return in;
    }
    public void setIn(DataInputStream in) {
        this.in = in;
    }
    public DataOutputStream getOut() {
        return out;
    }
    public void setOut(DataOutputStream out) {
        this.out = out;
    }
}