package iut.gon.agarioclient.server;
import iut.gon.agarioclient.model.Game;

import java.io.*;
import java.util.Random;
import java.util.Set;

public  class GameUpdater implements Runnable {
    private Set<ClientHandler> clientWriters;

    public GameUpdater(Set<ClientHandler> clients) {
        this.clientWriters = clients;
    }
    @Override
    public void run() {
        try {
            while (true) {
                Random random = new Random();
                TestVecteur vecteur = new TestVecteur(random.nextDouble(), random.nextDouble(), random.nextDouble());
                Game game = new Game();
                // Envoyer l'objet uniquement aux clients prêts
                for (ClientHandler client : Server.getClientHandlersSet()) {
                    Boolean isReady = Server.getClientReadyStatus().get(client.getClientId());
                    /*if (isReady != null && isReady) {  // Seulement si le client a répondu "pret"
                        try {
                            //client.sendObject(vecteur);
                            Serializer.sendObject(game, client.getOut());
                            //client.sendObject(game);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }*/
                }
                Thread.sleep(1000); // Pause d'une seconde entre chaque envoi (adapter si nécessaire)
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
