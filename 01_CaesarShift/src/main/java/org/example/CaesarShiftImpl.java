package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

public class CaesarShiftImpl implements CaesarShift {

    public static final List<Character> alphabetUpperCase = IntStream.rangeClosed('A', 'Z').mapToObj(var -> (char) var).toList();
    public static final List<Character> alphabetLowerCase = IntStream.rangeClosed('a', 'z').mapToObj(var -> (char) var).toList();

    static final HashMap<Character, Character> mapLowerCase = new HashMap<>();
    static final HashMap<Character, Character> mapUpperCase = new HashMap<>();

    public static Path outputPath = Path.of("results");

    public CaesarShiftImpl(String outputDir) throws IOException {
        CreateOutputDir(outputDir);
    }

    public CaesarShiftImpl() throws IOException {
        this(outputPath.toString());
    }

    static List<Character> ShiftAlphabet(List<Character> alphabet, int shift) {
        var shiftedAplhabet = new ArrayList<>(alphabet);
        Collections.rotate(shiftedAplhabet, shift);
        return shiftedAplhabet;
    }

    static void GetShiftedMaps(int shift, EncryptDirection direction) {
        var shiftedLowerCase = ShiftAlphabet(alphabetLowerCase, shift);
        var shiftedUpperCase = ShiftAlphabet(alphabetUpperCase, shift);
        CaesarShiftImpl.mapLowerCase.clear();
        CaesarShiftImpl.mapUpperCase.clear();

        for (int i = 0; i < shiftedUpperCase.size(); i++) {

            if (direction.equals(EncryptDirection.Encrypt)) {
                CaesarShiftImpl.mapLowerCase.put(alphabetLowerCase.get(i), shiftedLowerCase.get(i));
                CaesarShiftImpl.mapUpperCase.put(alphabetUpperCase.get(i), shiftedUpperCase.get(i));
            } else {
                CaesarShiftImpl.mapLowerCase.put(shiftedLowerCase.get(i), alphabetLowerCase.get(i));
                CaesarShiftImpl.mapUpperCase.put(shiftedUpperCase.get(i), alphabetUpperCase.get(i));
            }
        }
    }

    static void Execute(Reader reader, Writer writer) throws IOException {
        int oneCharInt;
        while ((oneCharInt = reader.read()) != -1) {
            char oneChar = (char) oneCharInt;
            if (mapLowerCase.containsKey(oneChar))
                writer.write(mapLowerCase.get(oneChar));
            else writer.write(mapUpperCase.getOrDefault(oneChar, oneChar));
        }
    }

    private static boolean DeleteDirectory(File directory) {
        File[] allContents = directory.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                DeleteDirectory(file);
            }
        }
        return directory.delete();
    }

    public void CreateOutputDir(String name) throws IOException {
        outputPath = Path.of(name);

        if (Files.exists(outputPath)) {
            DeleteDirectory(outputPath.toFile());
        }

        Files.createDirectory(outputPath.toAbsolutePath());
    }

    void Execute(String inputFile, String outputFile, int shift, EncryptDirection direction) throws IOException {

        Path inputPath = Path.of(inputFile);

        Path outputFilePath = Path.of(outputPath + File.separator + outputFile);

        try (Reader reader = new FileReader(inputPath.toFile()); Writer writer = new FileWriter(outputFilePath.toFile())) {
            GetShiftedMaps(shift, direction);
            Execute(reader, writer);
        }
    }

    public void Encrypt(String file, String encryptedFile, int shift) throws IOException {
        Execute(file, encryptedFile, shift, EncryptDirection.Encrypt);
    }

    public void DecryptFile(String file, String decryptedFile, int shift) throws IOException {
        Execute(file, decryptedFile, shift, EncryptDirection.Decrypt);
    }

    private String ExecuteWord(String word, int shift, EncryptDirection direction) throws IOException {
        String res;
        try (Reader reader = new StringReader(word); Writer writer = new StringWriter()) {
            GetShiftedMaps(shift, direction);
            Execute(reader, writer);
            res = writer.toString();
        }

        return res;
    }

    @Override
    public String DecryptWord(String word, int shift) throws IOException {
        return ExecuteWord(word, shift, EncryptDirection.Decrypt);
    }

    @Override
    public String EncryptWord(String word, int shift) throws IOException {
        return ExecuteWord(word, shift, EncryptDirection.Encrypt);
    }

}

