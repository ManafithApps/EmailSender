package com.abdullghani.emailsender;

public class EmailConfig {
    private String smtpHost = "smtp.gmail.com";
    private String port = "465";
    private String user;
    private String password;
    private boolean debug = false;

    private EmailConfig() {}

    public static class Builder {
        private final EmailConfig config = new EmailConfig();

        public Builder setHost(String host) { config.smtpHost = host; return this; }
        public Builder setPort(String port) { config.port = port; return this; }

        public Builder setCredentials(String user, String pass) {
            config.user = user;
            config.password = pass;
            return this;
        }
        public Builder debug(boolean enabled) { config.debug = enabled; return this; }
        public EmailConfig build() { return config; }
    }
    // Getters...
    public String getUser() { return user; }
    public String getPassword() { return password; }
    public String getSmtpHost() { return smtpHost; }
    public String getPort() { return port; }
    public boolean isDebug() { return debug; }
}