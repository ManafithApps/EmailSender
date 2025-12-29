package com.abdullghani.emailsender;

import android.os.Handler;
import android.os.Looper;
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import android.util.Log;
import android.net.Uri;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

public class RequestManager {
    private static final String TAG = "EmailSenderLib";
    private final Model model;

    public RequestManager(Model model) { this.model = model; }

    public RequestManager addAttachment(Uri uri) {
        model.attachmentUris.add(uri);
        return this;
    }

    public void send(OnSendEmailListener listener) {
        Handler mainHandler = new Handler(Looper.getMainLooper());

        new Thread(() -> {
            try {
                model.validate();
                if (model.debug) Log.d(TAG, "Starting email transport to: " + model.to);

                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");

                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(model.user, model.password);
                    }
                });

                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress(model.from != null ? model.from : model.user));
                mimeMessage.setSubject(model.subject);

                Multipart multipart = new MimeMultipart();

                // الجزء النصي
                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setContent(model.message, (model.isHtml ? "text/html" : "text/plain") + "; charset=UTF-8");
                multipart.addBodyPart(textPart);

                // معالجة المرفقات عبر URI
                for (Uri uri : model.attachmentUris) {
                    MimeBodyPart attachPart = new MimeBodyPart();
                    // استخدام InputStream للتعامل مع Scoped Storage في أندرويد
                    InputStream is = model.context.getContentResolver().openInputStream(uri);
                    File tempFile = createTempFile(uri);
                    copyInputStreamToFile(is, tempFile);

                    DataSource source = new FileDataSource(tempFile);
                    attachPart.setDataHandler(new DataHandler(source));
                    attachPart.setFileName(tempFile.getName());
                    multipart.addBodyPart(attachPart);
                }

                mimeMessage.setContent(multipart);
                mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(model.to));

                Transport.send(mimeMessage);
                if (model.debug) Log.i(TAG, "Email sent successfully!");
                mainHandler.post(listener::onSuccessful);

            } catch (Exception e) {
                if (model.debug) Log.e(TAG, "Error sending email: " + e.getMessage());
                mainHandler.post(() -> listener.onError(e.getMessage()));
            }
        }).start();
    }

    // ميثود مساعدة لتحويل URI لملف مؤقت ليتمكن JavaMail من قراءته
    private File createTempFile(Uri uri) {
        return new File(model.context.getCacheDir(), "attach_" + System.currentTimeMillis());
    }

    private void copyInputStreamToFile(InputStream in, File file) throws Exception {
        try (OutputStream out = new FileOutputStream(file)) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) out.write(buf, 0, len);
        }
    }
}