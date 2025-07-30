import java.io.*;
import java.util.TreeMap;
import java.lang.Math;

public class WordStatWordsShingles {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong input.");
            return;
        }

        String inputFileName = args[0], outputFileName = args[1];
        TreeMap <String, Integer> numberOfWords = new TreeMap<>();

        try {
            MyScanner in = new MyScanner(inputFileName, 1024);
            try {
                String s;
                while (in.hasNextWord()) {
                    s = in.nextWord().toLowerCase();
                    for (int i = 0; i <= Math.max(s.length() - 3, 0); i++) {
                        String sNew = s.substring(i, Math.min(i + 3, s.length()));
                        numberOfWords.put(sNew, numberOfWords.getOrDefault(sNew, 0) + 1);
                    }
                }
            }
            finally {
                in.close();
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }

        try {
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(outputFileName), "UTF8"),
                    1024);
            try {
                for (String S : numberOfWords.descendingKeySet()) {
                    out.write(S + " " + numberOfWords.get(S) + "\n");
                }
            }
            finally {
                out.close();
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
