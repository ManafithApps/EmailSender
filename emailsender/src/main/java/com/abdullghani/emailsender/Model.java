package com.abdullghani.emailsender;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class Model {
    public Context context;
    public String user, password, from, to, subject, message;
    public List<Uri> attachmentUris = new ArrayList<>();
    public boolean isHtml = true;
    public boolean debug = false;

    public void validate() throws Exception {
        if (user == null || password == null) throw new Exception("Credentials missing");
        if (to == null || !to.contains("@")) throw new Exception("Invalid recipient email");
        if (subject == null || subject.isEmpty()) throw new Exception("Subject is required");
    }



    public Model() {
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Uri> getAttachmentUris() {
        return attachmentUris;
    }

    public void setAttachmentUris(List<Uri> attachmentUris) {
        this.attachmentUris = attachmentUris;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public void setHtml(boolean html) {
        isHtml = html;
    }
}
