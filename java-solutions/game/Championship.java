package game;

import java.util.*;

public class Championship {
    private final int m, n, k;
    private int number;
    private final boolean isDiamond;
    private List<Player> upperDraw, bottomDraw;
    private final Map<Integer, Integer> res;

    public Championship(List<Player> upperDraw, final int m, final int n, final int k, final boolean isDiamond) {
        this.upperDraw = upperDraw;
        this.bottomDraw = new ArrayList<>();
        this.m = m;
        this.n = n;
        this.k = k;
        this.isDiamond = isDiamond;
        this.number = upperDraw.size();
        this.res = new HashMap<Integer, Integer>();
    }

    private int play(Player player1, Player player2) {
        int result = 0;
        do {
            if (player1.getId() != -1 && player2.getId() != -1) {
                System.out.println("Player " + player1.getId() + " as X's and player "
                        + player2.getId() + " as O's");
            }
            result = new Game(true, player1, player2).play(new TicTacToeBoard(m, n, k, isDiamond));
        }
        while (result == 0);
        return result;
    }

    private List<List<Player>> drawPlay(List<Player> players) {
        Collections.shuffle(players);
        List<List<Player>> ans = new ArrayList<>(List.of(new ArrayList<>(), new ArrayList<>()));
        for (int i = 0; i < players.size(); i += 2) {
            if (i + 1 >= players.size()) {
                ans.get(0).add(players.get(i));
                break;
            }
            int result = play(players.get(i), players.get(i + 1));
            if (result == players.get(i).getId()) {
                ans.get(0).add(players.get(i));
                ans.get(1).add(players.get(i + 1));
            } else {
                ans.get(1).add(players.get(i));
                ans.get(0).add(players.get(i + 1));
            }
        }
        return ans;
    }

    public void makeTournament() {
        if (upperDraw.size() == 1) {
            System.out.println(upperDraw.getFirst().getId() + " " + 1);
            return;
        }

        while (upperDraw.size() > 1 || bottomDraw.size() > 1) {
            List<List<Player>> upperResults = new ArrayList<>(List.of(new ArrayList<>(), new ArrayList<>()));
            List<List<Player>> bottomResults;
            if (upperDraw.size() > 1) {
                System.out.println("Upper draw matches:");
                upperResults = drawPlay(upperDraw);
                upperDraw = upperResults.getFirst();
            }
            if (bottomDraw.size() > 1) {
                System.out.println("Bottom draw matches:");
                bottomResults = drawPlay(bottomDraw);
                bottomDraw = bottomResults.get(0);
                number -= bottomResults.get(1).size();
                for (int i = 0; i < bottomResults.get(1).size(); i++) {
                    if (bottomResults.get(1).get(i).getId() != -1) {
                        res.put(bottomResults.get(1).get(i).getId(), number + 1);
                    }
                }
            }
            bottomDraw.addAll(upperResults.get(1));
        }

        if (upperDraw.size() == 1 && bottomDraw.size() == 1) {
            System.out.println("Final: player " + upperDraw.getFirst().getId() + " as X's and player "
                    + bottomDraw.getFirst().getId() + " as O's");
            int result = play(upperDraw.getFirst(), bottomDraw.getFirst());
            if (result == upperDraw.getFirst().getId()) {
                res.put(bottomDraw.getFirst().getId(), 2);
                res.put(upperDraw.getFirst().getId(), 1);
            } else {
                res.put(bottomDraw.getFirst().getId(), 1);
                res.put(upperDraw.getFirst().getId(), 2);
            }
        } else {
            res.put(upperDraw.getFirst().getId(), 1);
        }

        System.out.println();
        for (Map.Entry<Integer, Integer> entry : res.entrySet()) {
            System.out.println("Player " + entry.getKey() + " has " + entry.getValue() + " place");
        }
    }
}