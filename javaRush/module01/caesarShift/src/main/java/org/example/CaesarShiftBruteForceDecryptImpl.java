package org.example;

import java.io.IOException;
import java.util.HashSet;

public class CaesarShiftBruteForceDecryptImpl implements CaesarShiftBruteForceDecrypt {

    private final CaesarShift caesarShift;

    CaesarShiftBruteForceDecryptImpl(CaesarShift caesarShift) {
        this.caesarShift = caesarShift;
    }


    int TryGetShift(String file, String decryptedExample) throws IOException {
        int res = -1;
        HashSet<String> wordsInFile = new HashSet<>();

        HashSet<String> wordsInDecryptedFile = new HashSet<>();

        Utils.CollectWords(file, wordsInFile);
        Utils.CollectWords(decryptedExample, wordsInDecryptedFile);

        if (wordsInFile.isEmpty() || wordsInDecryptedFile.isEmpty())
            return res;

        String[] wordsInFileArray = wordsInFile.toArray(new String[0]);

        for (int i = 0; i < CaesarShiftImpl.alphabetUpperCase.size(); i++) {
            int shift = i;
            for (String s : wordsInFileArray) {
                String decryptedWord = caesarShift.DecryptWord(s, shift);
                if (wordsInDecryptedFile.contains(decryptedWord)) {
                    return shift;
                }
            }

        }

        return res;
    }

    /**
     * составляем список слов из примера decryptedExample
     * считываем слова из зашифрованного файла file
     * брутфорсим каждое слово
     * если нашли ключ, расшифровываем файл, иначе брутфорсом выдаем все варианты
     *
     */
    @Override
    public void Decrypt(String file, String decryptedExample, String decryptedFile) throws IOException {
        int shift = TryGetShift(file, decryptedExample);
        if (shift != -1) {
            caesarShift.DecryptFile(file, decryptedFile, shift);
        } else Decrypt(file, decryptedFile);
    }

    @Override
    public void Decrypt(String file, String decryptedFile) throws IOException {
        for (int i = 0; i < CaesarShiftImpl.alphabetUpperCase.size(); i++) {
            int shift = i;
            var splitted = decryptedFile.split("\\.");
            var fileName = splitted[0];
            var extension = splitted[1];
            caesarShift.DecryptFile(file, String.format("%s_%d.%s", fileName, shift, extension), shift);
        }
    }
}
