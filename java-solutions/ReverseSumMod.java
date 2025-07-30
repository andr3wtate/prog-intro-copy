import java.io.IOException;
import java.util.Arrays;

public class ReverseSumMod {

    public static int Sum(int a, int b, int mod) {
        return ((a % mod + b % mod) % mod + mod) % mod;
    }

    public static void main(String[] args) {
        IntList[] ans = new IntList[1];
        IntList arr = new IntList();
        int numAns = 0, colNum = 0, curLine = 0, mod = 1_000_000_007;
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
        IntList[] ansFinal = Arrays.copyOf(ans, numAns);
        int rowSum[] = new int[numAns];
        int colSum[] = new int[colNum];
        for (int i = 0; i < ansFinal.length; i++) {
            for (int j = 0; j < ansFinal[i].getLen(); j++) {
                rowSum[i] = Sum(rowSum[i], ansFinal[i].getInd(j), mod);
                colSum[j] = Sum(colSum[j], ansFinal[i].getInd(j), mod);
            }
        }
        for (int i = 0; i < ansFinal.length; i++) {
            for (int j = 0; j < ansFinal[i].getLen(); j++) {
                int numb = Sum(Sum(rowSum[i], colSum[j], mod), -ansFinal[i].getInd(j), mod);
                System.out.print(numb + " ");
            }
            System.out.println();
        }
    }
}