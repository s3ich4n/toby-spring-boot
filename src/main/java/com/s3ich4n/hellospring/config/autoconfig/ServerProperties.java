package com.s3ich4n.hellospring.config.autoconfig;

public class ServerProperties {
    private String contextPath;

    private int port;

    public int getPort() {
        return port;
    }
    public void setPort(int port) {

        this.port = port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
