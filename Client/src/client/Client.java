package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5555);
                      
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            Scanner option = new Scanner(System.in);
            
            System.out.println("====== Bem-Vindo =====");
            System.out.println("Selecione uma opcao: ");
            System.out.println("1 - Pedra");
            System.out.println("2 - Papel");
            System.out.println("3 - Tesoura");
            int optionSelected = option.nextInt();
            
            String msg;
            
            //Processamento
            switch (optionSelected) {
                case 1:
                    msg = "Pedra";
                    output.writeUTF(msg);
                    output.flush();
                    System.out.println("Cliente selecionou: Pedra");
                    break;
                    
                case 2:
                    msg = "Papel";
                    output.writeUTF(msg);
                    output.flush();
                    System.out.println("Cliente selecionou: Papel");
                    break;
                    
                case 3:
                    msg = "Tesoura";
                    output.writeUTF(msg);
                    output.flush();
                    System.out.println("Cliente selecionou: Tesoura");
                    break;
                    
                default:
                    System.out.println("Opção inválida");
            }
        
            msg = input.readUTF();
            System.out.println(msg);
                        
            output.close();;
            input.close();

            socket.close();
        } 
        catch (IOException ex) {
            System.out.println("Client error: " + ex);
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}