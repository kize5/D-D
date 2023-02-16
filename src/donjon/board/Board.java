package donjon.board;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    public List<Case> leBoard;
    int boardCases;
    public Board() {
        boardCases = 64;
        this.leBoard = new ArrayList<>();
        generateBoard();
    }
    private void generateBoard() {
        for (int i = 0; i < boardCases - 3; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);
            if (randomNum <= 70) {
                leBoard.add(new EmptyCase());
            } else if (randomNum < 85) {
                leBoard.add(new EnemiCase());
            } else leBoard.add(new LootCase());
        }
        leBoard.add(new EnemiCase());
        leBoard.add(new EnemiCase());
        leBoard.add(new EnemiCase());
        leBoard.add(new EnemiCase());
    }
}
