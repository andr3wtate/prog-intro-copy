import java.util.*;

public class E_1 {

    static void dfs(int v, ArrayList <ArrayList<Integer>> g, boolean[] used, int[] d, int[] p) {
        used[v] = true;
        for (int i = 0; i < g.get(v).size(); i++) {
            if (used[g.get(v).get(i)] == false) {
                d[g.get(v).get(i)] = d[v] + 1;
                p[g.get(v).get(i)] = v;
                dfs(g.get(v).get(i), g, used, d, p);
            }
        }
    }

    public static void main(String[] args) {
        final int inf = 1_000_000_000;
        final int N = 200_000;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList <ArrayList <Integer>> g = new ArrayList <>();
        ArrayList <Integer> ind = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList <>());
        for (int i = 0; i < n - 1; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            g.get(x).add(y);
            g.get(y).add(x);
        }
        for (int i = 0; i < m; i++) ind.add(in.nextInt() - 1);
        in.close();
        boolean[] used = new boolean[N];
        int[] d = new int[N];
        int[] p = new int[N];
        p[ind.get(0)] = -1;
        dfs(ind.get(0), g, used, d, p);
        int f = ind.get(0);
        for (int i = 0; i < m; i++) {
            if (d[ind.get(i)] > d[f]) f = ind.get(i);
        }
        ArrayList <Integer> path = new ArrayList<>();
        while (f != -1) {
            path.add(f);
            f = p[f];
        }
        int ans = path.get(path.size() / 2);
        d = new int[N];
        used = new boolean[N];
        dfs(ans, g, used, d, p);
        int len = d[ind.get(0)];
        for (int i = 0; i < m; i++) {
            if (d[ind.get(i)] != len) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
        System.out.println(ans + 1);
    }
}