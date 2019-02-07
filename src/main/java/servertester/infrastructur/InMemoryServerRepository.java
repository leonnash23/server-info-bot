package servertester.infrastructur;

import org.springframework.stereotype.Component;
import servertester.domain.Server;
import servertester.domain.ServerRepository;
import servertester.domain.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class InMemoryServerRepository implements ServerRepository {

    private final Queue<Server> globalQueue = new ConcurrentLinkedQueue<>();

    @PostConstruct
    public void init(){
        Server server = new Server();
        server.setUrl("http://212.22.64.195:8080/");
        server.setName("SalesmanGraph");
        User user = new User();
        user.setChatId(53506043);
        server.setUser(user);
        globalQueue.add(server);
    }


    @Override
    public List<Server> getUserServers(User user) {
        List<Server> servers = new ArrayList<>();
        for (Server server :
                globalQueue) {
            if(server.getUser().getChatId() == user.getChatId()){
                servers.add(server);
            }
        }
        return servers;
    }

    @Override
    public Server save(Server server) {
        globalQueue.add(server);
        return server;
    }
}
