import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class B_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        int s = -710 * 25000;
        for (int i = 0; i < n; i++) {
            System.out.println(s);
            s += 710;
        }
    }
}