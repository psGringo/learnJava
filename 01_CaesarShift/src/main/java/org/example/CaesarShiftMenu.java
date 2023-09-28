package org.example;

import java.io.IOException;
import java.util.Scanner;


public class CaesarShiftMenu {

    public static int GetMenuChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Utils.BuildMainMenu());
        return scanner.nextInt();
    }

    public static void Execute() {
        int selected = GetMenuChoice();
        int exitCode = 6;

        do {
            try {
                switch (selected) {
                    case 1 -> {
                        Encrypt();
                        selected = GetMenuChoice();
                    }
                    case 2 -> {
                        Decrypt();
                        selected = GetMenuChoice();
                    }
                    case 3 -> {
                        DecryptBrute();
                        selected = GetMenuChoice();
                    }
                    case 4 -> {
                        DecryptBruteOptional();
                        selected = GetMenuChoice();
                    }
                    case 5 -> {
                        DecryptStatistically();
                        selected = GetMenuChoice();
                    }
                    case 6 -> System.out.println("Successfully exited program");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                selected = GetMenuChoice();
            }

        } while (selected != exitCode);

    }

    public static void Encrypt() throws IOException {
        var fileName = Utils.GetFileName();
        var encryptedFileName = Utils.GetOutputFileName(fileName, "Encrypted");
        var shift = Utils.GetShiftKey();
        CaesarShift caesarShift = new CaesarShiftImpl();
        caesarShift.Encrypt(fileName, encryptedFileName, shift);
        System.out.printf("Successfully encrypted file %s, results in dir /results%n", fileName);
    }

    public static void Decrypt() throws IOException {
        var fileName = Utils.GetFileName();
        var decryptedFileName = Utils.GetOutputFileName(fileName, "Decrypted");
        var shift = Utils.GetShiftKey();
        CaesarShift caesarShift = new CaesarShiftImpl();
        caesarShift.DecryptFile(fileName, decryptedFileName, shift);
        String msg = String.format("Successfully decrypted file %s, results in dir /results", fileName);
        System.out.println(msg);
    }

    public static void DecryptBrute() throws IOException {
        var fileName = Utils.GetFileName();
        var decryptedFileName = Utils.GetOutputFileName(fileName, "Decrypted");
        CaesarShiftBruteForceDecrypt caesarShift = new CaesarShiftBruteForceDecryptImpl(new CaesarShiftImpl());
        caesarShift.Decrypt(fileName, decryptedFileName);
        var msg = String.format("Successfully brutally decrypted file %s, results in dir /results", fileName);
        System.out.println(msg);
    }

    public static void DecryptBruteOptional() throws IOException {
        var fileName = Utils.GetFileName();
        var decryptedFileName = Utils.GetOutputFileName(fileName, "Decrypted");
        var optionalFile = Utils.GetFileName();
        CaesarShiftBruteForceDecrypt caesarShift = new CaesarShiftBruteForceDecryptImpl(new CaesarShiftImpl());
        caesarShift.Decrypt(fileName, optionalFile, decryptedFileName);
        var msg = String.format("Successfully brutally decrypted file %s using optional file, results in dir /results", fileName);
        System.out.println(msg);
    }

    public static void DecryptStatistically() throws IOException {
        var fileName = Utils.GetFileName();
        String decryptedFileName = Utils.GetOutputFileName(fileName, "Decrypted");
        var optionalFile = Utils.GetFileName();
        CaesarShiftStatisticalDecrypt caesarShift = new CaesarShiftStatisticalDecryptImpl(new CaesarShiftImpl());
        caesarShift.Decrypt(fileName, optionalFile, decryptedFileName);
        String msg = String.format("Successfully brutally decrypted file %s using optional file, results in dir /results", fileName);
        System.out.println(msg);
    }
}
