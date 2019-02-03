package servertester.useceses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import servertester.domain.Server;
import servertester.domain.ServerRepository;
import servertester.domain.User;
import servertester.domain.UserRepository;

import java.util.List;

@Component
public class StatusAllAction implements Action {

    private final UserRepository userRepository;
    private final ServerRepository serverRepository;
    private final RequestWorker requestWorker;

    @Autowired
    public StatusAllAction(UserRepository userRepository, ServerRepository serverRepository, RequestWorker requestWorker) {
        this.userRepository = userRepository;
        this.serverRepository = serverRepository;
        this.requestWorker = requestWorker;
    }

    @Override
    public SendMessage accept(Update update) {
        User user = userRepository.getUser(update.getMessage().getChatId());
        List<Server> userServers = serverRepository.getUserServers(user);
        StringBuilder stringBuilder = new StringBuilder();
        for (Server server : userServers) {
            ServerInfo serverInfo = requestWorker.getServerInfo(server.getUrl());
            stringBuilder.append(String.format("%s — %s, %d mc\n\r", server.getName(), serverInfo.isOK(), serverInfo.getResponseTime()));
        }
        return new SendMessage(update.getMessage().getChatId(), stringBuilder.toString());
    }
}
