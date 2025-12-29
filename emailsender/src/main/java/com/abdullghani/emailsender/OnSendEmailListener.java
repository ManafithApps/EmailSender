package com.abdullghani.emailsender;

public interface OnSendEmailListener {
    void onSuccessful();
    void onError(String error);
}
