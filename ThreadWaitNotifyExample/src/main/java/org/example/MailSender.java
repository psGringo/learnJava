package org.example;

public class MailSender implements Runnable {

    private final Mail mail;

    public MailSender(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void run() {
        synchronized (mail) {
            mail.setMessage("hi, everyone");
            mail.notifyAll();
        }
    }
}
