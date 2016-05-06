import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.net.ConnectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class CsServer extends UnicastRemoteObject implements Service {

    private List<Queue> queues;

    public final static String QUEUENAME = "csQueue";
    private int clientCt;
    transient private Session s;

    protected CsServer() throws RemoteException {
        queues = new ArrayList<>();
        clientCt = 0;
        initConnexion();
    }

    protected CsServer(int port) throws RemoteException {
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
        return "Mega serveur de CSGO";
    }

    @Override
    public void accessService() throws RemoteException {
        System.out.println("Connection ... Connected!");
    }

    @Override
    public String subscribe() throws RemoteException {
        clientCt += 1;
        String queueName = QUEUENAME + clientCt;
        try {
            Queue queue = s.createQueue(queueName);
            queues.add(queue);

            MessageProducer sender = s.createProducer(queue);
            TextMessage message = s.createTextMessage("Bonjour client");
            sender.send(message);
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
}
