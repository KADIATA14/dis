import java.net.*;
import java.io.*;

public class Serveur {
    public static void main(String[] args) {
        try {
            // Création d'un objet ServerSocket pour écouter les connexions de clients
            ServerSocket serverSocket = new ServerSocket(2023);
            System.out.println("Le serveur est à l'écoute sur le port2023...");

            // Accepter une connexion de client
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connexion reçue du client: " + clientSocket.getInetAddress());

            // Création de flux d'entrée et de sortie pour la communication avec le client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            String message = in.readLine();
            System.out.println("Message du client :"+message);
            // Envoi d'un message au client
            out.println("Bienvenue sur mon serveur!");

            // Fermeture des flux et des connexions
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'exécution du serveur: " + e.getMessage());
        }
    }
}
