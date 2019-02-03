package servertester.useceses;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestWorker {

    private final HttpClient httpClient;

    @Autowired
    public RequestWorker(HttpClient httpClient) {
        this.httpClient = httpClient;
    }


    public ServerInfo getServerInfo(String uri) {
        ServerInfo serverInfo = new ServerInfo();
        try {
            long time = System.currentTimeMillis();
            HttpResponse response = httpClient.execute(new HttpGet(uri));
            time = System.currentTimeMillis() - time;
            int statusCode = response.getStatusLine().getStatusCode();
            serverInfo.setStatusCode(statusCode).setResponseTime(time);
            EntityUtils.consume(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverInfo;
    }
}
