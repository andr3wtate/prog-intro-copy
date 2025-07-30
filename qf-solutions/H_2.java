import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class H_2 {
    public static void main(String[] args) {
        final int inf = 1_000_000_000;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[200_000];
        int[] pref = new int[200_001];
        int mx = -1;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            mx = Math.max(mx, a[i]);
            pref[i + 1] = pref[i] + a[i];
        }
        int q = in.nextInt();
        int[] ans = new int[1_000_001];
        for (int i = 0; i < q; i++) {
            int x = in.nextInt();
            if (x < mx) {
                System.out.println("Impossible");
                continue;
            }
            if (ans[x] != 0) {
                System.out.println(ans[x]);
                continue;
            }
            int cnt = 0, curind = 0;
            while (curind < n) {
                int l = 0, r = n - curind + 1;
                while (l < r - 1) {
                    int m = (l + r) / 2;
                    if (pref[curind + m] - pref[curind] <= x) l = m;
                    else r = m;
                }
                cnt++;
                curind += l;
            }
            ans[x] = cnt;
            System.out.println(cnt);
        }
        in.close();
    }
}