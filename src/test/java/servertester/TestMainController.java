package servertester;

import org.junit.Assert;
import org.junit.Test;
import servertester.useceses.MainController;
import servertester.useceses.RequestWorker;

public class TestMainController {


    @Test
    public void testSendRequest() {
        MainController mainController = new MainController();
        String uri = "http://google.com";
        Assert.assertTrue(RequestWorker.getServerInfo(uri).isOK());
    }

    @Test
    public void testGetApi(){
        MainController mainController = new MainController();
        String botToken = mainController.getBotToken();
        Assert.assertNotNull(botToken);
        Assert.assertNotEquals("", botToken);
    }

}
