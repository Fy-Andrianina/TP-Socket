package server;
import java.net.*;
import javax.swing.*;
import java.awt.*;;
import java.io.*;
import java.util.*;
import client.ClientManager;

public class Server {
    int port;
    DataInputStream in;
    DataOutputStream out;
    ArrayList<ClientManager> clientsmanage=new ArrayList<>();
    ArrayList<ServerManager> serverManager=new ArrayList<>();
    ArrayList<Socket> serversec=new ArrayList<>();
    byte[][] tab;
    byte [] name;
    
    public byte[][] getTab() {
        return tab;
    }
   public byte[] getName() {
    return name;
}
public void setName(byte[] name) {
    this.name = name;
}
 public void setTab(byte[][] tab) {
        this.tab = tab;
    }
    public ArrayList<Socket> getServersec() {
        return serversec;
    }
    public void setServersec(ArrayList<Socket> serversec) {
        this.serversec = serversec;
    }
    public DataOutputStream getOut() {
        return out;
    }
    public void setOut(DataOutputStream out) {
        this.out = out;
    }
    public DataInputStream getIn() {
        return in;
    }
    public void setIn(DataInputStream in) {
        this.in = in;
    }
    public Server(int port){
        this.port=port;
        System.out.println(port);
        start();
    }
    public void start(){
        ServerSocket server=null;
       
            try {
                server = new ServerSocket(port, 5);
            } catch (IOException e) {
                System.err.println("Impossible de créer un ServerSocket");
            
            }
            JFrame frame=new JFrame("Cote serveur");
            JPanel pan=new JPanel();
            frame.add(pan);
            frame.setVisible(true);
            frame.setSize(new Dimension(500,300));
            frame.setFocusableWindowState(false);
          
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
            Socket client=null;
            while(true){
                try{
                 client=server.accept();
               ObjectInputStream read=new ObjectInputStream(client.getInputStream());
               String v = (String)read.readObject();
               pan.add(new JLabel("A new "+ v +" is connected...."));
               
                if(v.equalsIgnoreCase("client")){
                    ClientManager manage=new ClientManager(client,this);
                    clientsmanage.add(manage);
                    manage.setId(clientsmanage.size());
                    manage.start();
                  
                }
                if(v.equalsIgnoreCase("server")){
                    serversec.add(client);
                  
                    ServerManager manage=new ServerManager(client,this);
                    serverManager.add(manage);
                    manage.setId(serverManager.size());
                    manage.start();
                   
                  
                }
                
                    
                }
                catch (Exception e) {
                    
                   try {
                    client.close();
                   } catch (Exception io) {
                      io.printStackTrace();
                   }
                    e.printStackTrace();
        
                }
            
        }
        
    }
    public ArrayList<ClientManager> getClientsmanage() {
        return clientsmanage;
    }
    public void setClientsmanage(ArrayList<ClientManager> clientsmanage) {
        this.clientsmanage = clientsmanage;
    }
    public ArrayList<ServerManager> getServerManager() {
        return serverManager;
    }
    public void setServerManager(ArrayList<ServerManager> serverManager) {
        this.serverManager = serverManager;
    }
}