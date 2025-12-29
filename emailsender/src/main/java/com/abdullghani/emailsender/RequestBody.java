package com.abdullghani.emailsender;

public class RequestBody {
    private final Model model;
    public RequestBody(Model model) { this.model = model; }

    public RequestManager message(String message, boolean isHtml) {
        this.model.message = message;
        this.model.isHtml = isHtml;
        return new RequestManager(model);
    }
}
