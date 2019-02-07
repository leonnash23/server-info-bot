package servertester.infrastructur;

import org.springframework.stereotype.Component;
import servertester.domain.User;
import servertester.domain.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class InMemoryUserRepository implements UserRepository {

    private final Queue<User> globalQueue = new ConcurrentLinkedQueue<>();


    @PostConstruct
    public void init() {
        User user = new User();
        user.setChatId(53506043);
        globalQueue.add(user);
    }


    @Override
    public User getUser(long id) {
        for (User user :
                globalQueue) {
            if(user.getChatId() == id){
                return user;
            }
        }
        return null;
    }

    @Override
    public User save(User user) {
        globalQueue.add(user);
        return user;
    }
}
