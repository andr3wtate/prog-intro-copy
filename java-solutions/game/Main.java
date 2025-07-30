package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter field size and k");
        int m = in.nextInt(), n = in.nextInt(), k = in.nextInt();
        System.out.println("Type \"I give up\" to give up.");
        System.out.println("Type \"How about a draw\" if you want to ask your opponent for a draw." + System.lineSeparator());
        List<Player> list = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            list.add(new SequentialPlayer(i + 1));
        }
        list.add(new HumanPlayer(18));
        Championship championship = new Championship(list, m, n, k, true);
        championship.makeTournament();
    }
}
