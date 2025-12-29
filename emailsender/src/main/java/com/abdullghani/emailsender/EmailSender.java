package com.abdullghani.emailsender;

import android.content.Context;

public class EmailSender {


    public static Configure with(Context context) {
        if (context == null) throw new IllegalArgumentException("Context cannot be null");
        Model model = new Model();
        model.context = context;
        return new Configure(model);
    }
}