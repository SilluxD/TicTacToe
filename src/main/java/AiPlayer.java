import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.util.Pair;

public class AiPlayer {

  private Game game;
  private int playerNumber;

  /**
   * Constructor creating the AiPlayer.
   *
   * @param game         Game-object the AiPlayer takes part in
   * @param playerNumber The player-number the AiPlayer uses
   */
  public AiPlayer(Game game, int playerNumber) {
    this.game = game;
    this.playerNumber = playerNumber;
  }

  /**
   * Make a move and click on a field on the board.
   */
  public void makeMove() {
    Pair<Integer, Integer> coordinates = this.getCoordinates();
    this.game.aiClickField(coordinates.getKey(), coordinates.getValue());
  }

  /**
   * The ai checks all unoccupied spaces and puts moves it could make into weighted lists. Winning
   * the game has the highest priority, Stopping the opponents win comes second, taking the middle
   * third, taking the edge-points fourth and taking the sides last.
   *
   * <p>The highest possible List of moves is taken and the possible moves are shuffled in order
   * to make the ai act more random.
   *
   * @return Pair of Integer coordinates where the ai wants to make it's move.
   */
  private Pair<Integer, Integer> getCoordinates() {
    int[][] board = this.game.getBoard();
    int opp = (this.playerNumber + 1) % 2;
    List<Pair<Integer, Integer>> prio1 = new ArrayList<>();
    List<Pair<Integer, Integer>> prio2 = new ArrayList<>();
    List<Pair<Integer, Integer>> prio3 = new ArrayList<>();
    List<Pair<Integer, Integer>> prio4 = new ArrayList<>();
    List<Pair<Integer, Integer>> prio5 = new ArrayList<>();

    for (int y = 0; y < board.length; y++) {
      for (int x = 0; x < board[y].length; x++) {
        if (board[y][x] == -1) {

          if (board[y][(x + 1) % 3] == this.playerNumber
              && board[y][(x + 2) % 3] == this.playerNumber) {
            // <win>
            // horizontal line
            prio1.add(new Pair<>(x, y));

          } else if (board[(y + 1) % 3][x] == this.playerNumber
              && board[(y + 2) % 3][x] == this.playerNumber) {
            // vertical line
            prio1.add(new Pair<>(x, y));

          } else if (x == 0 && y == 0 && board[1][1] == this.playerNumber
              && board[2][2] == this.playerNumber) {
            // diagonal line
            prio1.add(new Pair<>(x, y));
          } else if (x == 2 && y == 0 && board[1][1] == this.playerNumber
              && board[2][0] == this.playerNumber) {
            prio1.add(new Pair<>(x, y));
            // </win>
          } else if (board[y][(x + 1) % 3] == opp

              && board[y][(x + 2) % 3] == opp) {
            // <block enemy's win>
            // horizontal line
            prio2.add(new Pair<>(x, y));

          } else if (board[(y + 1) % 3][x] == opp
              && board[(y + 2) % 3][x] == opp) {
            // vertical line
            prio2.add(new Pair<>(x, y));

          } else if (x == 0 && y == 0 && board[1][1] == opp
              && board[2][2] == opp) {
            // diagonal line
            prio2.add(new Pair<>(x, y));
          } else if (x == 2 && y == 0 && board[1][1] == opp
              && board[2][0] == opp) {
            prio2.add(new Pair<>(x, y));
            // </block enemies win>
          }

          if (x == 1 && y == 1) {
            // middle
            prio3.add(new Pair<>(x, y));
          } else if (Math.abs(x - y) == 2 || y == 0 && x == 0 || y == 2 && x == 2) {
            // edges
            prio4.add(new Pair<>(x, y));
          } else {
            // sides
            prio5.add(new Pair<>(x, y));
          }
        }
      }
    }

    if (prio1.size() > 0) {
      Collections.shuffle(prio1);
      return prio1.get(0);
    } else if (prio2.size() > 0) {
      Collections.shuffle(prio1);
      return prio2.get(0);
    } else if (prio3.size() > 0) {
      Collections.shuffle(prio1);
      return prio3.get(0);
    } else if (prio4.size() > 0) {
      Collections.shuffle(prio1);
      return prio4.get(0);
    } else {
      Collections.shuffle(prio5);
      return prio5.get(0);
    }
  }
}
