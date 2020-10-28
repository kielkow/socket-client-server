package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5555);
                      
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            while(true) {
                String msg = "Não preciso de você servidor!";

                output.writeUTF(msg);
                
                output.flush();

                msg = input.readUTF();
                System.out.println("Message from server: " + msg);
            }
            
//            output.close();
//            input.close();

//            socket.close();
        } 
        catch (IOException ex) {
            System.out.println("Client error: " + ex);
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
