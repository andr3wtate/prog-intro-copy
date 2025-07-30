import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.lang.Math;

public class Wspp {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong input.");
            return;
        }

        String inputFileName = args[0], outputFileName = args[1];
        LinkedHashMap<String, IntList> numberOfWords = new LinkedHashMap<>();

        try {
            MyScanner in = new MyScanner(inputFileName, 1024);
            try {
                String s;
                int curInd = 1;
                while (in.hasNextWord()) {
                    s = in.nextWord().toLowerCase();
                    if (numberOfWords.get(s) == null) {
                        numberOfWords.put(s, new IntList());
                    }
                    numberOfWords.get(s).add(curInd++);
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
                            new FileOutputStream(outputFileName), StandardCharsets.UTF_8),
                    1024);
            try {
                for (String S : numberOfWords.keySet()) {
                    out.write(S + " ");
                    numberOfWords.get(S).printAll(out);
                }
            }
            finally {
                out.close();
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}