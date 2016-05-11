import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;
import java.net.ConnectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceServer extends CallBackService {

    private List<Queue> queues;

    public final static String QUEUENAME = "randomQueue";
    private int clientCt;
    transient private Session s;

    protected DiceServer() throws RemoteException {
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
        return "Random number generator";
    }

    @Override
    public Serializable accessService() throws RemoteException {
        Random rand = new Random();
        return rand.nextInt(100);
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
