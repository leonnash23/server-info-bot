package servertester.useceses;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import servertester.useceses.actions.Action;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Router {

    private final Map<String, Action> routes;

    @Autowired
    public Router(List<Action> actions) {
        routes = new ConcurrentHashMap<>();
        for (Action action :
                actions) {
            routes.put(action.getKey(), action);
        }
    }


    public SendMessage route(Update update) {
        Action action = getActionFromUpdate(update);
        if(Objects.isNull(action)){
            return new SendMessage(update.getMessage().getChatId(), "Неизвестная команда.");
        }
        return action.accept(update);
    }

    Action getActionFromUpdate(Update update) {
        String command = getCommand(update);
        return getActionFromCommand(command);
    }

    private String getCommand(Update update) {
        return update.getMessage().getText().split(" ")[0];
    }

    private Action getActionFromCommand(String command) {
        return routes.get(command);
    }
}
