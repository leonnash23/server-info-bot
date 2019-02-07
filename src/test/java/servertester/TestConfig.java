package servertester;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import servertester.domain.ServerRepository;
import servertester.domain.UserRepository;
import servertester.infrastructur.InMemoryServerRepository;
import servertester.infrastructur.InMemoryUserRepository;
import servertester.useceses.RequestWorker;
import servertester.useceses.Router;
import servertester.useceses.actions.Action;
import servertester.useceses.actions.AddServerAction;
import servertester.useceses.actions.StatusAllAction;

import java.util.Arrays;
import java.util.List;

@Configuration
public class TestConfig {

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public ServerRepository serverRepository() {
        return new InMemoryServerRepository();
    }

    @Bean
    public RequestWorker requestWorker() {
        return new RequestWorker(HttpClientBuilder.create().build());
    }


    @Bean
    public List<Action> actions(UserRepository userRepository,
                                ServerRepository serverRepository,
                                RequestWorker requestWorker) {
        return Arrays.asList(
                new AddServerAction(userRepository, serverRepository),
                new StatusAllAction(userRepository, serverRepository, requestWorker));
    }


    @Bean
    public Router router(List<Action> actions) {
        return new Router(actions);
    }
}
