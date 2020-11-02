package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

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
        
            String[] array = new String[]{"Pedra","Papel","Tesoura"};
            String resposta = getRandom(array);
            
            String msg = input.readUTF();            
            System.out.println("Servidor escolheu: " + resposta);
 
            switch (msg) {
                case "Papel":
                    if (resposta.equals("Pedra")) {
                        output.writeUTF("Cliente ganhou!!!!");
                        output.flush();
                        System.out.println("Cliente ganhou!!!");
                    }
                    else if (resposta.equals("Tesoura")) {
                        output.writeUTF("Servidor ganhou!!!!");
                        output.flush();
                        System.out.println("Servidor ganhou!!!!");
                    } 
                    else {
                        output.writeUTF("Empatou!!!");
                        output.flush();
                        System.out.println("Empatou!!!");
                    }
                    
                    break;

                case "Pedra":
                    if (resposta.equals("Papel")) {
                        output.writeUTF("Servidor ganhou!!!!");
                        output.flush();
                        System.out.println("Servidor ganhou!!!!");
                    }                  
                    else if (resposta.equals("Tesoura")) {
                        output.writeUTF("Cliente ganhou!!!!");
                        output.flush();
                        System.out.println("Cliente ganhou!!!!");
                    } 
                    else {
                        output.writeUTF("Empatou!!!");
                        output.flush();
                        System.out.println("Empatou!!!");
                    }
                    
                    break;

                case "Tesoura":
                    if (resposta.equals("Papel")) {
                        output.writeUTF("Cliente ganhou!!!!");
                        output.flush();
                        System.out.println("Cliente ganhou!!!!");
                    }                   
                    else if (resposta.equals("Pedra")) {
                        output.writeUTF("Servidor ganhou!!!!");
                        output.flush();
                        System.out.println("Servidor ganhou!!!!");
                    } 
                    else {
                        output.writeUTF("Empatou!!!");
                        output.flush();
                        System.out.println("Empatou!!!");
                    }                    
                    break;

                default:
                    System.out.println("Opção invalida");
            }            
            output.close();
            input.close();
        }
        catch(IOException e) {
            System.out.println("Problem to do conection: " + socket.getInetAddress());
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            closeSocket(socket);
        }
    }
    
    public static String getRandom(String[] array) {
        int value = new Random().nextInt(array.length);
        return array[value];
    }
    
    public static void main(String[] args) {
        try {
            Server server = new Server();
            System.out.println("Wait conection...");

            server.createServerSocket(5555);

            Socket socket = server.waitConection();            
            server.doConection(socket);
        } 
        catch(IOException e) {
            System.out.println(e);
        }
    }
}