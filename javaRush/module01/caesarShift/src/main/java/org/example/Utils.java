package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    static boolean IsAlphabeticalWord(String word) {
        return CaesarShiftImpl.alphabetUpperCase.contains(word.toCharArray()[0])
                || CaesarShiftImpl.alphabetLowerCase.contains(word.toCharArray()[0]);
    }

    public static void CollectWords(String file, HashSet<String> hashSet) throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {

            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                for (String word : line.split(" ")) {
                    if (!word.isEmpty() && IsAlphabeticalWord(word)) {
                        hashSet.add(word);
                    }
                }
            }
        }
    }

    public static void CollectWords(String file, HashMap<String, Integer> hashmap) throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {

            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                for (String word : line.split(" ")) {
                    if (!word.isEmpty() && IsAlphabeticalWord(word)) {
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

    public static String BuildMainMenu() {
        MenuBuilder builder = new MenuBuilder();
        return builder.AddLine("Welcome to caesar encryption example:")
                .AddLine("Menu:")
                .AddLine("1 - Encrypt")
                .AddLine("2 - Decrypt")
                .AddLine("3 - Decrypt Brute Force")
                .AddLine("4 - Decrypt Brute Force with example file")
                .AddLine("5 - Decrypt Statistically with example file")
                .AddLine("6 - Exit")
                .Build();
    }

    static void ValidateFileExists(String file) throws FileNotFoundException {
        String msg = String.format("file %s not found", file);
        if (Files.notExists(Path.of(file))) throw new FileNotFoundException(msg);
    }

    public static String GetFileName() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name");
        String file = scanner.nextLine();
        ValidateFileExists(file);
        return file;
    }

    public static int GetShiftKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shift key");
        return scanner.nextInt();
    }

    public static String GetOutputFileName(String fileName, String postFix) {
        String outputFileName;
        if (fileName.contains(".")) {
            var splitted = fileName.split("\\.");
            var name = splitted[0];
            var extension = splitted[1];
            outputFileName = String.format("%s%s.%s", name, postFix, extension);
        } else
            outputFileName = String.format("%s%s", fileName, postFix);
        return outputFileName;
    }
}
