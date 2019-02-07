package servertester.useceses;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import servertester.domain.User;

@Component
public class MessageWorker {

    public SendMessage createNewUserHello(User user){
        return new SendMessage(user.getChatId(), "Добро пожаловать!");
    }

}
