import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class J_1 {
    public static void main(String[] args) {
        final int inf = 1_000_000_000;
        Scanner in = new Scanner(System.in);
        String[] a = new String[500];
        char[][] ans = new char[500][500];
        int n = in.nextInt();
        in.nextLine();
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLine();
            for (int j = 0; j < n; j++) {
                ans[i][j] = '0';
            }
        }
        in.close();
        for (int len = 1; len < n; len++) {
            for (int l = 0; l < n - len; l++) {
                int r = l + len, cur = 0;
                for (int m = l + 1; m < r; m++) {
                    if (ans[l][m] == '1') {
                        cur += a[m].charAt(r) - '0';
                        cur %= 10;
                    }
                }
                if (cur != a[l].charAt(r) - '0') {
                    ans[l][r] = '1';
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ans[i][j]);
            }
            System.out.println();
        }
    }
}