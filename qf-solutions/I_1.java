import java.util.ArrayList;
import java.util.Scanner;

public class I_1 {
    public static void main(String[] args) {
        final int inf = 1_000_000_000;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] x = new int[1000];
        int[] y = new int[1000];
        int[] h = new int[1000];
        int mnh = inf, mxh = -inf, mnw = inf, mxw = -inf;
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
            h[i] = in.nextInt();
            mnh = Math.min(mnh, y[i] - h[i]);
            mxh = Math.max(mxh, y[i] + h[i]);
            mnw = Math.min(mnw, x[i] - h[i]);
            mxw = Math.max(mxw, x[i] + h[i]);
        }
        in.close();
        System.out.println((mnw + mxw) / 2 + " " + (mxh + mnh) / 2 + " " + (Math.max(mxw - mnw, mxh - mnh) + 1) / 2);
    }
}