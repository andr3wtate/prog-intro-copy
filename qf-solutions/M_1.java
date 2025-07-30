import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class M_1 {
    public static void main(String[] args) {
        final int inf = 1_000_000_000;
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int[] a = new int[2000];
        for (; t > 0; t--) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            HashMap <Integer, Integer> cnt = new HashMap<>();
            cnt.put(a[n - 1], 1);
            long ans = 0;
            for (int j = n - 2; j >= 1; j--) {
                for (int i = j - 1; i >= 0; i--) {
                    ans += cnt.getOrDefault(a[j] + a[j] - a[i], 0);
                }
                cnt.put(a[j], cnt.getOrDefault(a[j], 0) + 1);
            }
            System.out.println(ans);
        }
        in.close();
    }
}