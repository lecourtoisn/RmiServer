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
        ConnectionFactory factory = new ActiveMQConnectionFactory("user","user","tcp://localhost:61616/");
        javax.jms.Connection connection = factory.createConnection("user", "user");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        connection.start();

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
            System.out.println("Security manager installed.");
        }
        else {
            System.out.println("Security manager already exists.");
        }

        IServer server = (IServer) Naming.lookup("rmi://localhost:4000/Registry");

        Service csgo = (Service) server.lookup("Dice");
        Service overwatch = (Service) server.lookup("Clock");
        Data someData = (Data) server.lookup("SomeData");
        Serializable sNews = server.lookup("News");

        System.out.println(csgo.getInfo());
        System.out.println(overwatch.getInfo());
        /* Random generator */
        System.out.println(csgo.accessService());
        System.out.println(csgo.accessService());

        /* Clock */
        System.out.println(overwatch.accessService());

        /*  */
        System.out.println(someData.getData());

        try {
            Service news = (Service) sNews;
            news.accessService();
        } catch (ClassCastException e) {
            System.out.println("Not a service");
            Data news = (Data) sNews;
            System.out.println(news.getData());
        }

        for (String s : (server.getLastInfos(1))) {
            System.out.println(s);
        }
        for (String s : (server.getLastService(1))) {
            System.out.println(s);
        }

        String queueName = overwatch.subscribe();

        Queue queue = session.createQueue(queueName);
        MessageConsumer consumer = session.createConsumer(queue);
        while(true) {
            TextMessage messageRecu = (TextMessage) consumer.receive();
            System.out.println(messageRecu.getText());
        }
    }
}

