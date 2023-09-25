package org.example;

import java.io.IOException;

public interface CaesarShift {
    void Encrypt(String file, String encryptedFile, int shift) throws IOException;

    void DecryptFile(String file, String decryptedFile, int shift) throws IOException;

    String DecryptWord(String word, int shift) throws IOException;

    String EncryptWord(String word, int shift) throws IOException;

    void PrepareOutputPath(String name) throws IOException;

}
