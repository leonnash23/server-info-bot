package servertester.useceses;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import servertester.TestConfig;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class RouterTest {

    @Autowired
    private Router router;

    @Test
    public void getActionFromUpdateTest() {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        when(update.getMessage()).thenReturn(message);
        when(message.getText()).thenReturn("/statusAll");
        when(message.getChatId()).thenReturn(53506043L);
        Assert.assertEquals("/statusAll", router.getActionFromUpdate(update).getKey());

        when(message.getText()).thenReturn("/add");
        Assert.assertEquals("/add", router.getActionFromUpdate(update).getKey());
    }
}