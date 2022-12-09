package server;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class ServerSecond {
    Socket socketServerSecond;
    DataInputStream in;
    DataOutputStream out;

    public ServerSecond(String host,int port){
        try{
            this.socketServerSecond=new Socket(host,port);
            ObjectOutputStream p=new ObjectOutputStream(socketServerSecond.getOutputStream());
            p.writeObject("server");
            p.flush();

            this.in = new DataInputStream(this.socketServerSecond.getInputStream());
            this.out = new DataOutputStream(this.socketServerSecond.getOutputStream());
            
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
           
        ServerSecond servc=new ServerSecond(IP,realport);
             
    }
     

    public void start(){
        JFrame frame=new JFrame("Cote Server Secondaire");
        frame.setSize(new Dimension(500,300));
        frame.setFocusableWindowState(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
    public Socket getSocketServerSecond() {
        return socketServerSecond;
    }
    public void setSocketServerSecond(Socket socketServerSecond) {
        this.socketServerSecond = socketServerSecond;
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