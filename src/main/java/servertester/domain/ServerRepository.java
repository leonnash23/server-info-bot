package servertester.domain;

import java.util.List;

public interface ServerRepository {

    List<Server> getUserServers(User user);

    Server save(Server server);

}
