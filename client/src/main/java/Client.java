import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

/*
* Options
* -Djava.security.policy="client/secPolicy.policy" -Djava.rmi.server.codebase=http://DESKTOP-OHAS6O1:2001/
* */
public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, JMSException {

        /* Connexion au RMI classique pour recuperer notre portail generique de partage */
        ConnectionFactory factory = new ActiveMQConnectionFactory("user","user","tcp://localhost:61616/");
        IServer server = (IServer) Naming.lookup("rmi://localhost:4000/Registry");

        /* Connexion aux services JMS */
        javax.jms.Connection connection = factory.createConnection("user", "user");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();

        /* Creation d'un security manager */
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
            System.out.println("Security manager installed.");
        }
        else {
            System.out.println("Security manager already exists.");
        }


        // Recuperation de nos deux services Dice et Clock
        Service dice = (Service) server.lookup("Dice");
        Service clock = (Service) server.lookup("Clock");

        /* Recuperation de deux donnees SomeData et News */
        Data someData = (Data) server.lookup("SomeData");
        Serializable sNews = server.lookup("News");

        /* Utilisation des services */
        /* Dice Simulator, genere un nombre aleatoire entre 0 et 99 */
        System.out.println(dice.getInfo());

        System.out.println("Nombre en 0 et 99 : " + dice.accessService());
        System.out.println("Nombre en 0 et 99 : " + dice.accessService());

        /* TalkingClock, renvoie la date */
        System.out.println(clock.getInfo());
        System.out.println("Date : " + clock.accessService());

        /* Lecture des donnees de DiceSimulator*/
        System.out.println("Donnee importante : " + someData.getData());

        /* Lecture de l'element stocke avec le tag News gerant le cas ou sont type dynamique est incertain */
        try {
            Service news = (Service) sNews;
            news.accessService();
        } catch (ClassCastException e) {
            Data news = (Data) sNews;
            System.out.println(news.getData());
        }

        /* Recuperation des tags des N dernieres informations requetees */
        System.out.println("N dernieres informations requetees");
        for (String s : (server.getLastInfos(1))) {
            System.out.println(s);
        }

        /* Recuperation des tags des N derniers services requetes */
        System.out.println("N derniers services requetes");
        for (String s : (server.getLastService(1))) {
            System.out.println(s);
        }

        /* Abonnement aux dernieres informations de clock, qui sont l'heure toutes les 3 secondes */
        System.out.println("Abonnement au service de TalkingClock");
        String queueName = clock.subscribe();

        /* Recuperations des nouvelles informations */
        System.out.println("Recuperations des nouvelles donnees de TalkingClock");
        Queue queue = session.createQueue(queueName);
        MessageConsumer consumer = session.createConsumer(queue);
        while(true) {
            TextMessage messageRecu = (TextMessage) consumer.receive();
            System.out.println(messageRecu.getText());
        }
    }
}

