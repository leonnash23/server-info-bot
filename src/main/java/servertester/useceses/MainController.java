package servertester.useceses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import servertester.domain.User;
import servertester.domain.UserRepository;

@Component
public class MainController extends TelegramLongPollingBot {


    private final UserRepository userRepository;
    private final MessageWorker messageWorker;
    private final Router router;

    @Autowired
    public MainController(UserRepository userRepository, MessageWorker messageWorker, Router router) {
        this.userRepository = userRepository;
        this.messageWorker = messageWorker;
        this.router = router;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String userName = update.getMessage().getFrom().getUserName();
        User user = userRepository.getUser(chatId);
        if(user == null){
            try {
                execute(new SendMessage(chatId, String.format("Привет, %s!☀️\nБот пока в разработке," +
                        " возвращайся немного позже\nComing soon...\uD83D\uDD28", userName)));
                return;
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        try {
            execute(router.route(update));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "ServerInfo";
    }

    @Override
    public String getBotToken() {
        return System.getenv("SERVER_INFO_BOT_API_KEY");
    }
}
