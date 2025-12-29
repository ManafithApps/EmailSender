package com.abdullghani.emailsender;

public class RequestRecipient {
    private final Model model;
    public RequestRecipient(Model model) { this.model = model; }

    public RequestSubject to(String to) {
        this.model.to = to;
        return new RequestSubject(model);
    }
}
