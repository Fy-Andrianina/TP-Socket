package client;
import java.io.*;

import java.net.*;
import java.util.ArrayList;
import java.util.Vector;

import server.Server;

public class ClientManager extends Thread{
     public static ArrayList<ClientManager> clientsmanage=new ArrayList<>();
    private Server server;
    private Socket socket;
    private BufferedReader read;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectInputStream input;
    private String nom;
    private int id;

    
    public void setId(int id) {
        this.id = id;
    }
    public ClientManager(Socket socket,Server server){
       try{
            this.socket=socket;
            this.server=server;
            this.out=new DataOutputStream(this.socket.getOutputStream());
            this.in=new DataInputStream(this.socket.getInputStream());
         
       }catch(IOException e){
          e.printStackTrace();
           CloseAll(this.socket,in,out);
       }
    }

    public void removeclients(){
        clientsmanage.remove(this);
     

    }
    public void CloseAll(Socket socket,DataInputStream in,DataOutputStream out){
        removeclients();
        try{
            if(in!=null){
                in.close();
            }
            if(out!=null){
                out.close();
            }
            if(socket!=null){
                socket.close();
            }

        }catch(IOException e){
        
                e.printStackTrace();
            }
    }

   @Override
    public void run() {
        
      //  System.out.println("run");
        try {
           while(socket.isConnected()){
        
            if(in.readBoolean()==true){
              //  System.out.println("boolean true");
                String namefile=in.readUTF();
              //  System.out.println(namefile+" avant toute operation");
                Vector<byte[]> bigvec=new Vector<>();
                int ligne=0;

              for(int i=0;i<this.server.getServerManager().size();i++){
                byte[] b=this.server.getServerManager().get(i).getFromrepertory(i,namefile);
                ligne=ligne + b.length;
                bigvec.add(b);
              }

              byte[] bigtab=new byte[ligne];
              bigtab=concatByte(bigtab,bigvec);
              
              out.write(bigtab);
              int indice=(this.id-1);
              String path="E://lecons S3//Reseaux java//New one//Download//client"+indice;
              System.out.println(path+" dans Client Manager");
              File directory=new File(path);

              if(!directory.exists()){
                    directory.mkdirs();
              }

              FileOutputStream foos=new FileOutputStream("E://lecons S3//Reseaux java//New one//Download//client"+indice+"//"+namefile);
              foos.write(bigtab);
             //this.server.setAsk(null);

            }else{
                    int longname=in.readInt();
                    System.out.println(longname+" namelong");
                    byte[] name=new byte[longname];
                    in.read(name);

                    String names= new String(name);
                    System.out.println(names+" dans telechargement");

                    int nb=in.readInt();
                    byte[] b=new byte[nb];

                    in.read(b);
                    //System.out.println(new String(b)+"taille ");
                    
                    int taille=b.length/this.server.getServerManager().size();
                    byte[][] bout=new byte[this.server.getServerManager().size()][taille];
                    int rang=0;
                    int debut=0;
                    int a=0;
                    int init=taille;
        
                    while(taille<=nb){
                       // System.out.println(nb);
                                for(int i=debut;i<taille;i++){
                                
                                    bout[rang][a]=b[i];
                                    
                                    a++;
                                }
                             System.out.println(new String(bout[rang])+" iciiii "+rang);
                                a=0;
                                rang++;
                                debut=taille;
                                taille=taille+init;
                    }
                    this.server.setTab(bout);
                    this.server.setName(name);
                   System.out.println(bout[0].length+new String(name)+" apres departagement");
                  }  //  this.sendToSecondary(bout,name);
                }
        } catch (Exception e) {
            e.printStackTrace();
             CloseAll(this.socket,in,out);
            
        }
    }
    public byte[] concatByte(byte[] tab,Vector vec){
        int i=0;
        int u=0;
       while(u<vec.size()){
            byte[] b=(byte[])vec.get(u);
            for(int a=0;a<b.length;a++){
                tab[i]=b[a];
                i++;
            }
            u++;
        }
    
        return tab;
    }
   
    

    

}