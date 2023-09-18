import java.io.*;

public class StreamExperiments {

    private static final String inputFile = "C:\\New\\in.txt";
    private static final String outputFile = "C:\\New\\out.txt";

    public static void CopyPastStreamReader() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        OutputStreamWriter isw = new OutputStreamWriter(System.out);

        char[] buffer = new char[200];

        while (isr.ready()) {
            var read = isr.read(buffer, 0, 100);
            isw.write(buffer, 0, read);
        }
        isw.flush();
    }

    public static void CopyPastFileReader() throws IOException {

        try (
                FileInputStream fis = new FileInputStream(inputFile);
                FileOutputStream fos = new FileOutputStream(outputFile)) {
            byte[] buffer = new byte[100];

            while (fis.available() > 0) {
                var read = fis.read(buffer);
                fos.write(buffer, 0, read);
            }
        }
    }

    public static void BufferedReader() throws IOException {

        try (
                BufferedReader br = new BufferedReader(new FileReader(inputFile));
                //BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));) {
            char[] buffer = new char[100];
            while (br.ready()) {
                var read = br.read(buffer);
                bw.write(buffer, 0, read);
            }
        }
    }

}
