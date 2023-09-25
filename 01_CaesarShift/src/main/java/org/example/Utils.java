package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Utils {
    public static boolean IsFilesEqual(String fileOne, String fileTwo) throws IOException {
        Path fileOnePath = Path.of(fileOne);
        Path fileTwoPath = Path.of(fileTwo);

        byte[] f1 = Files.readAllBytes(fileOnePath);
        byte[] f2 = Files.readAllBytes(fileTwoPath);

        return Arrays.equals(f1, f2);
    }

    static boolean isAlphabeticalWord(String word) {
        return CaesarShiftImpl.alphabetUpperCase.contains(word.toCharArray()[0])
                || CaesarShiftImpl.alphabetLowerCase.contains(word.toCharArray()[0]);
    }

    public static void collectWords(String file, HashSet<String> hashSet) throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {

            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                for (String word : line.split(" ")) {
                    if (!word.isEmpty() && isAlphabeticalWord(word)) {
                        hashSet.add(word);
                    }
                }
            }
        }
    }

    public static void collectWords(String file, HashMap<String, Integer> hashmap) throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {

            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                for (String word : line.split(" ")) {
                    if (!word.isEmpty() && isAlphabeticalWord(word)) {
                        if (!hashmap.containsKey(word)) {

                            hashmap.put(word, 1);
                        } else {
                            int frequency = hashmap.get(word);
                            hashmap.put(word, ++frequency);
                        }

                    }
                }
            }
        }
    }
}
