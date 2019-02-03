package servertester.useceses;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {


    @Bean
    public HttpClient getHttpClient(){
        return HttpClientBuilder.create().build();
    }

}
