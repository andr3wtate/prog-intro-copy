import javax.annotation.processing.SupportedSourceVersion;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class WordStatInput {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong input.");
            return;
        }

        String inputFileName = args[0], outputFileName = args[1];
        HashMap <String, Integer> numberOfWords = new HashMap<>();
        ArrayList <String> order = new ArrayList<String>();

        try {
            MyScanner in = new MyScanner(inputFileName, 1024);
            try {
                String s;
                while (in.hasNextWord()) {
                    s = in.nextWord().toLowerCase();
                    if (numberOfWords.containsKey(s)) {
                        numberOfWords.put(s, numberOfWords.get(s) + 1);
                    }
                    else {
                        order.add(s);
                        numberOfWords.put(s, 1);
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
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), "UTF8"), 1024);
            try {
                for (int i = 0; i < order.size(); i++) {
                    out.write(order.get(i) + " " + numberOfWords.get(order.get(i)) + "\n");
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