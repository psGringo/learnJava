package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Demo {
    static final String file = "hallelujah.txt";
    static final String encryptedFile = "hallelujahEncrypted.txt";

    static final String decryptedFile = "hallelujahDecrypted.txt";

    static final int shift = 1;

    public static void EncryptDecrypt() {
        try {
            CaesarShift caesarShift = new CaesarShiftImpl();
            caesarShift.Encrypt(file, encryptedFile, shift);
            caesarShift.DecryptFile(encryptedFile, decryptedFile, shift);
            System.out.println("// --- RESULT ---");
            System.out.printf("Encrypted and decrypted files are equal: %s", Utils.IsFilesEqual(file, decryptedFile));
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

    public static void EncryptDecryptBruteForce() throws IOException {
        CaesarShift caesarShift = new CaesarShiftImpl();
        CaesarShiftBruteForceDecrypt caesarShiftBrute = new CaesarShiftBruteForceDecryptImpl(caesarShift);

        try {
            caesarShift.Encrypt(file, encryptedFile, shift);
            var encryptedFilePath = Path.of(CaesarShiftImpl.outputPath + File.separator + encryptedFile);
            caesarShiftBrute.Decrypt(encryptedFilePath.toString(), decryptedFile);
        } catch (IOException e) {
            System.out.println(e.getCause());
        }

    }

    public static void EncryptDecryptBruteForceOptional() throws IOException {
        CaesarShift caesarShift = new CaesarShiftImpl();
        CaesarShiftBruteForceDecrypt caesarShiftBrute = new CaesarShiftBruteForceDecryptImpl(caesarShift);

        try {
            caesarShift.Encrypt(file, encryptedFile, shift);
            var encryptedFilePath = Path.of(CaesarShiftImpl.outputPath + File.separator + encryptedFile);
            caesarShiftBrute.Decrypt(encryptedFilePath.toString(), file, decryptedFile);
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
    }

    public static void EncryptDecryptStatisticalApproach() throws IOException {
        CaesarShift caesarShift = new CaesarShiftImpl();
        CaesarShiftStatisticalDecrypt caesarShiftStatisticalDecrypt = new CaesarShiftStatisticalDecryptImpl(caesarShift);
        try {
            caesarShift.Encrypt(file, encryptedFile, shift);
            var encryptedFilePath = Path.of(CaesarShiftImpl.outputPath + File.separator + encryptedFile);
            caesarShiftStatisticalDecrypt.Decrypt(encryptedFilePath.toString(), file, decryptedFile);
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
    }
}
