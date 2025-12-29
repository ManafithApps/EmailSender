package com.abdullghani.emailsender;

public class RequestCredential {
    private final Model model;

    public RequestCredential(Model model) {
        this.model = model;
    }

    public RequestSender setCredentials(String user, String password) {
        model.setUser(user);
        model.setPassword(password);
        return new RequestSender(model);
    }

}
