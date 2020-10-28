package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    
    private void createServerSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    
    private Socket waitConection() throws IOException {
        Socket socket = serverSocket.accept();
        
        return socket;
    }
    
    private void closeSocket(Socket socket) throws IOException {
        socket.close();
    }
    
    private void doConection(Socket socket) throws IOException {
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            while (true) {
                String msg = input.readUTF();
                System.out.println("Message from client: " + msg);
            
                output.writeUTF("Precisa sim!");
                
                output.flush();
            }
            
//            output.close();
//            input.close();
        }
        catch(IOException e) {
            System.out.println("Problem to do conection: " + socket.getInetAddress());
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            closeSocket(socket);
        }
    }
    
    public static void main(String[] args) {
        try {
            Server server = new Server();
            System.out.println("Wait conection...");

            server.createServerSocket(5555);

            Socket socket = server.waitConection();
            System.out.println("Client conected.");
            
            server.doConection(socket);

            System.out.println("Client desconected.");
        } 
        catch(IOException e) {
            System.out.println(e);
        }
    }
}
