import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    static List<PrintWriter> clients = new ArrayList<PrintWriter>();
    static List<String> noms = new ArrayList<String>();

    public static void main(String[] args) {
        try {
            // Création d'un objet ServerSocket pour écouter les connexions de clients
            ServerSocket serverSocket = new ServerSocket(2023);
            System.out.println("Le serveur est à l'écoute sur le port 2023...");

            while (true) {
                // Accepter une connexion de client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connexion reçue du client: " + clientSocket.getInetAddress());

                // Création de flux d'entrée et de sortie pour la communication avec le client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Demande du nom du client
                //out.println("Veuillez entrer votre nom :");
                String nom = in.readLine();


                // Ajout du client à la liste des clients
                clients.add(out);
                noms.add(nom);

                // Envoi de la liste des clients connectés à tous les clients
                for (PrintWriter client : clients) {
                  
                    client.println("CONNECTE " + nom);
                }

                // Création d'un thread pour gérer la communication avec le client
                Thread t = new Thread(() -> {
                    try {
                        String message;
                        while ((message = in.readLine()) != null) {
                            // Envoi du message à tous les clients
                            for (PrintWriter client : clients) {
                                System.out.println(client);
                                System.out.println(nom);
                                // System.out.println(nom + " : " + message);ssvasvd

                                client.println(nom + " : " + message);
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Erreur lors de la communication avec le client: " + e.getMessage());
                    } finally {
                        // Suppression du client de la liste des clients et envoi de la liste actualisée à tous les clients
                        clients.remove(out);
                        for (PrintWriter client : clients) {
                            client.println("DECONNECTE " + nom);
                        }
                    }
                });
                t.start();
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de l'exécution du serveur: " + e.getMessage());
        }
    }
}
