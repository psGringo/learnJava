package org.example;

public class WaitNotifyExample {
    public static void execute() {
        System.out.println("starting...");
        Mail mail = new Mail();

        var readerThread1 = new Thread(new MailReader(mail));
        var readerThread2 = new Thread(new MailReader(mail));
        var readerThread3 = new Thread(new MailReader(mail));

        readerThread1.start();
        readerThread2.start();
        readerThread3.start();

        var senderThread = new Thread(new MailSender(mail));
        senderThread.start();

        System.out.println("everybody waiting for 3 sec...");
        try {
            Thread.sleep(3000);
            //  mail.notifyAll(); // doesn't work from main thread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
