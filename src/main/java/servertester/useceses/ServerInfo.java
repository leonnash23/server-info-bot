package servertester.useceses;

import org.apache.http.HttpStatus;

@SuppressWarnings("unused")
public class ServerInfo {

    private int statusCode;
    private long responseTime;

    ServerInfo setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public long getResponseTime() {
        return responseTime;
    }

    @SuppressWarnings("UnusedReturnValue")
    ServerInfo setResponseTime(long responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isOK() {
        return statusCode == HttpStatus.SC_OK;
    }
}
