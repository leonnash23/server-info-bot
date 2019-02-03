package servertester.useceses;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import servertester.domain.Server;
import servertester.domain.ServerRepository;
import servertester.domain.User;
import servertester.domain.UserRepository;

@Component
public class AddServerAction implements Action{

    private final UserRepository userRepository;
    private final ServerRepository serverRepository;


    @Autowired
    public AddServerAction(UserRepository userRepository, ServerRepository serverRepository) {
        this.userRepository = userRepository;
        this.serverRepository = serverRepository;
    }


    public SendMessage accept(Update update){
        User user = userRepository.getUser(update.getMessage().getChatId());
        Server server = new Server();
        server.setUser(user);
        server.setUrl(update.getMessage().getText().split(" ")[1]);
        server.setName(update.getMessage().getText().split(" ")[2]);
        serverRepository.save(server);
        return new SendMessage(user.getChatId(), "Сервер сохранён!");
    }
}
