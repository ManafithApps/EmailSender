package com.abdullghani.emailsender;

public class RequestSubject {
    private final Model model;
    public RequestSubject(Model model) { this.model = model; }

    public RequestBody subject(String subject) {
        this.model.subject = subject;
        return new RequestBody(model);
    }
}
