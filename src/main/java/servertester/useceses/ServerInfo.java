package servertester.useceses;

import org.apache.http.HttpStatus;

public class ServerInfo {

    private int statusCode;
    private long responseTime;

    ServerInfo setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    ServerInfo setResponseTime(long responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    long getResponseTime() {
        return responseTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isOK() {
        return statusCode == HttpStatus.SC_OK;
    }
}
