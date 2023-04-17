import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Création d'un objet Socket pour se connecter au serveur sur le port 8080
            Socket socket = new Socket("localhost", 2023);
            System.out.println("Connecté au serveur sur le port 2023...");

            // Création de flux d'entrée et de sortie pour la communication avec le serveur
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // System.out.println("Client denarre" + Client.toString());
            // Lecture du message envoyé par le serveur
            out.println("Bonjour serveur");
            
            String message = in.readLine();
            System.out.println("Message reçu du serveur: " + message);

            // Fermeture des flux et de la connexion
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'exécution du client: " + e.getMessage());
        }
    }
}
