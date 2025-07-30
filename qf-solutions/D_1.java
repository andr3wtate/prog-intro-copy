import java.util.*;

public class D_1 {

    static int mod = 998_244_353;
    static final int N = 100_010;

    public static int binPow(long a, long n) {
        if (n == 0) return 1;
        if (n % 2 == 1) return (int) (a * binPow(a, n - 1) % mod);
        else {
            long b = binPow(a, n / 2);
            return (int) (b * b % mod);
        }
    }

    public static int mul(long a, long b) {
        return (int) (a * b % mod);
    }

    public static int sum(int a, int b) {
        return (a + b > mod) ? a + b - mod : a + b;
    }

    public static int dif(int a, int b) {
        return (a - b < 0) ? a - b + mod : a - b;
    }

    public static int getR(int n, int k) {
        if (n % 2 == 1) {
            return mul(binPow(k, (n + 1) / 2), n);
        } else {
            return sum(mul(binPow(k, n / 2 + 1), n / 2), mul(binPow(k, n / 2), n / 2));
        }
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int k = in.nextInt();
        in.close();
        int[] d = new int[N];
        d[1] = k;
        for (int i = 2; i <= n; i++) {
            d[i] = getR(i, k);
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    d[i] = dif(d[i], mul(i / j, d[j]));
                    if (i / j != i && i / j != j) d[i] = dif(d[i], mul(j, d[i / j]));
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    ans = sum(ans, d[j]);
                    if (i / j != j) ans = sum(ans, d[i / j]);
                }
            }
        }
        System.out.println(ans);
    }
}