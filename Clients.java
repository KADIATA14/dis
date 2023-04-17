import java.net.*;
import java.io.*;

public class Clients {
    public static void main(String[] args) {
        try {
            // Création d'un objet Socket pour la connexion au serveur
            Socket socket = new Socket("localhost", 2023);

            // Création de flux d'entrée et de sortie pour la communication avec le serveur
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Demande du nom du client
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Veuillez entrer votre nom :");
            String nom = stdIn.readLine();

            // Envoi du nom du client au serveur
            out.println(nom);

            // Lecture de la réponse du serveur
            String message = in.readLine();
            System.out.println(message);

            // Création d'un thread pour la lecture des messages du serveur
            Thread t = new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException e) {
                    System.err.println("Erreur lors de la lecture des messages du serveur: " + e.getMessage());
                }
            });
            t.start();

            // Envoi des messages saisis par l'utilisateur
            while (true) {
                String userInput = stdIn.readLine();
                out.println(userInput);
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la communication avec le serveur: " + e.getMessage());
        }
    }
    
}
