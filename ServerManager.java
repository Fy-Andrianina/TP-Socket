package server;
import java.io.*;
import java.net.*;
import java.nio.file.Files;

public class ServerManager extends Thread{
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private int id;

   

    public void setId(int id) {
        this.id = id;
    }

    public ServerManager( Socket socket,Server server) {
        this.server = server;
        this.socket = socket;
        try{
            this.in=new DataInputStream(this.socket.getInputStream());
            this.out=new DataOutputStream(this.socket.getOutputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

    @Override
    public void run() {
        
     //  System.out.println("run");
        try{
            while(socket.isConnected()){
                 System.out.println("huhu");
               if(this.server.getName()!=null){
                
                 String indice=String.valueOf((this.id-1));
                 System.out.println(indice+" le rep");
                        String path="E://lecons S3//Reseaux java//New one//backup//rep"+indice+"//"+new String(this.server.getName());
                        String dir="E://lecons S3//Reseaux java//New one//backup//rep"+indice;
                      System.out.println(path+"  repertory dans servermanager");

                        File directory=new File(dir);
                        if(!directory.exists()){
                            directory.mkdirs();
                        }

                        FileOutputStream foos=new FileOutputStream(path);
                        this.server.setName(null);
                        foos.write(this.server.getTab()[this.id-1]);  
             }                
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        
    }
    public byte[] getFromrepertory(int indice,String name){
       
        String path="backup//rep"+String.valueOf(indice);
        File rep=new File(path);
        
        File theright=null;
        byte [] b=null;
      //  System.out.println(rep.getName()+" repertory ");
        try{
            if(rep.isDirectory()){
                File[] files=rep.listFiles();
                //      System.out.println(path);
             //  System.out.println(files.length);
                for(int i=0;i<files.length;i++){
                  //  System.out.println(files[i].getName()+"liste fichier");

                    if(files[i].getName().equalsIgnoreCase(name)){
                        theright=files[i];
                        System.out.println(files[i].getName());
                        b=Files.readAllBytes(theright.toPath());
                        System.out.println(b.length+"longueur du fichier");
                        break;
                    }
                }
            
             }
        }catch(IOException e){
            e.printStackTrace();
        }
        return b;
    }
    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
}
