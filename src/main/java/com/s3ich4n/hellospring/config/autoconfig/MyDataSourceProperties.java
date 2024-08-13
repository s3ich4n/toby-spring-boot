package com.s3ich4n.hellospring.config.autoconfig;


import com.s3ich4n.hellospring.config.MyConfigurationProperties;

@MyConfigurationProperties(prefix = "data")
public class MyDataSourceProperties {
    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String driverClassName;

    private String url;

    private String username;

    private String password;
}
