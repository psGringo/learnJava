package org.example;

public class MailReader implements Runnable {

    private final Mail mail;

    public MailReader(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void run() {
        try {
            synchronized (mail) {
                mail.wait(); // this will stop all threads, having this monitor
            }
            System.out.printf("thread %s, message: %s%n", Thread.currentThread().getName(), mail.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
