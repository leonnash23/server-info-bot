package servertester.domain;

import java.util.List;

public interface ServerRepository {

    List<Server> getUserServers(User user);

    @SuppressWarnings("UnusedReturnValue")
    Server save(Server server);

}
