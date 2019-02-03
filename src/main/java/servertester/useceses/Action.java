package servertester.useceses;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Action {

    SendMessage accept(Update update);
}
