package Server;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientToServer {
    
    private Socket socket = null;
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;
    
    public ClientToServer(String addy, int port){
        try{
            socket = new Socket(addy, port);
            System.out.println("connection has been established");
            
            inputStream = new DataInputStream(System.in);
            
            outputStream = new DataOutputStream(socket.getOutputStream());
             
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
        
        String line = "";
        
		//while loop to read text input
        while(!line.equals("over")){
            try{
                line = inputStream.readLine();
                outputStream.writeUTF(line);
            } 
            catch (IOException ex) {
                System.out.println(ex);
            }
        }
        try{
            inputStream.close();
            outputStream.close();
            socket.close();
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args){
        ClientToServer client = new ClientToServer("127.0.0.1", 5002);
    }
    
    
}
