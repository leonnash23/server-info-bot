package servertester.useceses;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Router {

    private Map<String, Action> routes;

    private final AddServerAction addServerAction;
    private final StatusAllAction statusAllAction;

    @Autowired
    public Router(AddServerAction addServerAction, StatusAllAction statusAllAction) {
        this.addServerAction = addServerAction;
        this.statusAllAction = statusAllAction;
    }


    @PostConstruct
    public void init(){
        routes = new ConcurrentHashMap<>();
        routes.put("/add", addServerAction);
        routes.put("/statusAll", statusAllAction);
    }


    public SendMessage route(Update update) {
        String command = update.getMessage().getText().split(" ")[0];
        Action action = routes.get(command);
        if(Objects.isNull(action)){
            return new SendMessage(update.getMessage().getChatId(), "Неизвестная команда.");
        }
        return action.accept(update);
    }
}
