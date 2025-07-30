import java.io.IOException;
import java.util.Arrays;

public class ReverseMaxModOctDec {

    public static long mod(final long a, final long mod) {
        return ((a % mod + mod) % mod);
    }

    public static int max(final int a, final int b) {
        if (mod(a, 1_000_000_007) >= mod(b, 1_000_000_007)) {
            return a;
        }
        return b;
    }

    public static void main(final String[] args) {
        IntList[] ans = new IntList[1];
        IntList arr = new IntList();
        int numAns = 0, colNum = 0, curLine = 0;
        try {
            final MyScanner sc = new MyScanner(1024);
            try {
                boolean fl;
                do {
                    fl = sc.hasNextInt();
                    final int cnt = sc.getLineNo();
                    while (curLine < cnt) {
                        if (numAns == ans.length) {
                            ans = Arrays.copyOf(ans, 2 * numAns);
                        }
                        arr.fit();
                        ans[numAns++] = arr;
                        colNum = Math.max(colNum, arr.getLen());
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
        final IntList[] ansFinal = Arrays.copyOf(ans, numAns);
        final int[] rowMax = new int[numAns];
        final int[] colMax = new int[colNum];
        for (int i = 0; i < ansFinal.length; i++) {
            for (int j = 0; j < ansFinal[i].getLen(); j++) {
                rowMax[i] = max(rowMax[i], ansFinal[i].getInd(j));
                colMax[j] = max(colMax[j], ansFinal[i].getInd(j));
            }
        }
        for (int i = 0; i < ansFinal.length; i++) {
            for (int j = 0; j < ansFinal[i].getLen(); j++) {
                System.out.print(Integer.toOctalString(max(colMax[j], rowMax[i])) + "o ");
            }
            System.out.println();
        }
    }
}