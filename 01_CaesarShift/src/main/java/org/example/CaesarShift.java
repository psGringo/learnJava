package org.example;

import java.io.IOException;

public interface CaesarShift {
    public void Encrypt(String file, String encryptedFile, int shift) throws IOException;

    public void DecryptFile(String file, String decryptedFile, int shift) throws IOException;

    public String DecryptWord(String word, int shift) throws IOException;

    public String EncryptWord(String word, int shift) throws IOException;

    public void PrepareOutputPath(String name) throws IOException;

}
