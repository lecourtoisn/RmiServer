import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class OverwatchServer extends UnicastRemoteObject implements Service {

    private List<Queue> queues;

    public final static String QUEUENAME = "overwatchQueue";
    private int clientCt;
    transient private Session s;

    protected OverwatchServer() throws RemoteException {
        queues = new ArrayList<>();
        clientCt = 0;
        initConnexion();
    }

    protected OverwatchServer(int port) throws RemoteException {
        super(port);
        queues = new ArrayList<>();
        clientCt = 0;
        initConnexion();
    }

    private void initConnexion() {
        ConnectionFactory factory = new ActiveMQConnectionFactory("user", "user", "tcp://localhost:61616/");
        try {
            Connection connection = factory.createConnection("user", "user");
            s = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getInfo() throws RemoteException {
        return "Mega serveur de Overwatch";
    }

    @Override
    public void accessService() throws RemoteException {
        System.out.println("Connection ... Connected to Overwatch!");
    }

    @Override
    public String subscribe() throws RemoteException {
        clientCt += 1;
        String queueName = QUEUENAME + clientCt;
        try {
            Queue queue = s.createQueue(queueName);
            queues.add(queue);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return queueName;
    }

    public List<Queue> getQueues() {
        return queues;
    }

    public void setQueues(List<Queue> queues) {
        this.queues = queues;
    }

    public int getClientCt() {
        return clientCt;
    }

    public void setClientCt(int clientCt) {
        this.clientCt = clientCt;
    }

    public void publish(String msg) {
        for (Queue queue : queues) {
            try {
                MessageProducer sender = s.createProducer(queue);
                TextMessage message = s.createTextMessage(msg);
                sender.send(message);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
