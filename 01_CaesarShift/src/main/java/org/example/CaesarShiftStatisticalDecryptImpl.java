package org.example;

import java.io.IOException;
import java.util.*;

public class CaesarShiftStatisticalDecryptImpl implements CaesarShiftStatisticalDecrypt {

    private final CaesarShift caesarShift;

    public CaesarShiftStatisticalDecryptImpl(CaesarShift caesarShift) {

        this.caesarShift = caesarShift;
    }

    static class WordEntry {
        private String key;
        private Integer frequency;

        public WordEntry(String key, Integer frequency) {
            this.key = key;
            this.frequency = frequency;
        }

    }

    private int TryGetShift(HashMap<String, Integer> wordsFrequencyMap, HashSet<String> words) throws IOException {
        int res = -1;

        List<WordEntry> entriesList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordsFrequencyMap.entrySet()) {
            entriesList.add(new WordEntry(entry.getKey(), entry.getValue()));
        }

        // Сортируем по частоте использования слов
        entriesList.sort(Comparator.comparingInt(o -> o.frequency));

        for (WordEntry entry : entriesList) {
            for (int i = 0; i < CaesarShiftImpl.alphabetUpperCase.size(); i++) {
                int shift = i;
                String encryptedWord = caesarShift.EncryptWord(entry.key, shift);
                if (words.contains(encryptedWord)) {
                    return shift;
                }
            }
        }

        return res;
    }

    @Override
    public void Decrypt(String file, String decryptedExample, String decryptedFile) throws IOException {

        HashMap<String, Integer> wordsFrequencyMap = new HashMap<>();
        HashSet<String> words = new HashSet<>();
        Utils.CollectWords(decryptedExample, wordsFrequencyMap);
        Utils.CollectWords(file, words);

        int shift = TryGetShift(wordsFrequencyMap, words);
        if (shift != -1) {
            caesarShift.DecryptFile(file, decryptedFile, shift);
        } else {
            System.out.println("Ключ не найден методом статистического анализа");
        }

    }
}
