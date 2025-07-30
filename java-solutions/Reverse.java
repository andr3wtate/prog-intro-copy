import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.Arrays;

public class Reverse {

    public static void main(String[] args) {
        IntList[] ans = new IntList[1];
        IntList arr = new IntList();
        int numAns = 0, curLine = 0;
        try {
            MyScanner sc = new MyScanner(1024);
            try {
                boolean fl = true;
                do {
                    fl = sc.hasNextInt();
                    int cnt = sc.getLineNo();
                    while (curLine < cnt) {
                        if (numAns == ans.length) {
                            ans = Arrays.copyOf(ans, 2 * numAns);
                        }
                        arr.fit();
                        ans[numAns++] = arr;
                        arr = new IntList();
                        curLine++;
                    }
                    if (!fl) {
                        break;
                    }
                    arr.add(sc.nextInt());
                } while (true);
            }
            finally {
                sc.close();
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        IntList[] ansFinal = Arrays.copyOf(ans, numAns);
        for (int i = ansFinal.length - 1; i >= 0; i--) {
            ansFinal[i].printr();
            System.out.print('\n');
        }
    }
}