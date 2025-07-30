import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WsppCountEvenDigits {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong input.");
            return;
        }

        String inputFileName = args[0], outputFileName = args[1];
        HashMap<String, IntListInd> numberOfWords = new HashMap<>();
        HashMap<String, IntList> curNumb = new HashMap<>();

        try {
            MyScanner in = new MyScanner(inputFileName, 1024);
            try {
                String s;
                int curInd = 1, lineInd = 0;
                boolean fl = true;
                do {
                    fl = in.hasNextIntWord();
                    if (in.getLineNo() != lineInd || !fl) {
                        curInd = 1;
                        lineInd = in.getLineNo();
                        for (Map.Entry<String, IntList> cur : curNumb.entrySet()) {
                            String curs = cur.getKey();
                            IntList curList = cur.getValue();
                            if (numberOfWords.get(curs) == null) {
                                numberOfWords.put(curs, new IntListInd(lineInd, curList.getInd(0)));
                            }
                            numberOfWords.get(curs).addWords(curList.getLen());
                            for (int i = 1; i < curList.getLen(); i += 2) {
                                numberOfWords.get(curs).add(curList.getInd(i));
                            }
                        }
                        curNumb = new HashMap<>();
                    }
                    if (!fl) {
                        break;
                    }
                    s = in.nextIntWord().toLowerCase();

                    IntList v = curNumb.get(s);
                    if (v == null) {
                        v = new IntList();
                        curNumb.put(s, v);
                    }
                    v.add(curInd++);
                } while (true);
            } finally {
                in.close();
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

        List<Map.Entry<String, IntListInd>> ans = new ArrayList(numberOfWords.entrySet());
        Collections.sort(ans, new Comparator<Map.Entry<String, IntListInd>>() {
            @Override
            public int compare(Map.Entry<String, IntListInd> o1, Map.Entry<String, IntListInd> o2) {
                IntListInd l1 = o1.getValue();
                IntListInd l2 = o2.getValue();
                if (l1.getTrueNumb() != l2.getTrueNumb()) {
                    return l1.getTrueNumb() - l2.getTrueNumb();
                } else if (l1.getRowIndFirst() != l2.getRowIndFirst()) {
                    return l1.getRowIndFirst() - l2.getRowIndFirst();
                }
                return l1.getColIndFirst() - l2.getColIndFirst();
            }
        });

        try {
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(outputFileName), StandardCharsets.UTF_8),
                    1024);

            try {
                for (Map.Entry<String, IntListInd> x : ans) {
                    out.write(x.getKey() + " " + x.getValue().getTrueNumb());
                    for (int i = 0; i < x.getValue().getLen(); i++) {
                        out.write(" " + x.getValue().getInd(i));
                    }
                    out.write(System.lineSeparator());
                }
            } finally {
                out.close();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}