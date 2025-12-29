package com.abdullghani.emailsender;


public class RequestSender {
    private final Model model;
    public RequestSender(Model model) { this.model = model; }

    public RequestRecipient from(String from) {
        this.model.from = from;
        return new RequestRecipient(model);
    }
}
