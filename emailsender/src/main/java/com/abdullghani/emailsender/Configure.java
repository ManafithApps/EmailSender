package com.abdullghani.emailsender;

public class Configure {
    private final Model model;

    public Configure(Model model) {
        this.model = model;
    }

    public RequestSender setConfigure(EmailConfig config){
        model.user = config.getUser();
        model.password = config.getPassword();
        model.debug = config.isDebug();
        return new RequestSender(model);

    }

}
