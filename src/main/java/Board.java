import lombok.Getter;

public class Board {

  @Getter
  private int[][] board;
  private int emptyFields;
  @Getter
  private int winner = -1;

  /**
   * Constructor for a new board. Initializes the board with values indicating all fields are not
   * taken by a player.
   */
  public Board() {
    this.board =
        new int[][]{
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1}
        };
    this.emptyFields = 9;
  }

  private boolean isOccupied(int x, int y) {
    return this.board[y][x] != -1;
  }

  /**
   * Attempt to click a field on the board specified by its position. Will not work if that space is
   * already taken or if the game has already ended.
   *
   * @param x      x-coordinate of the field
   * @param y      y-coordinate of the field
   * @param player player who is taking that field
   * @return boolean whether taking the field was successful
   */
  public boolean clickField(int x, int y, int player) {
    if (0 <= x && x < 3 && 0 <= y && y < 3) {
      if (this.isOccupied(x, y) || this.winner != -1) {
        return false;
      } else {
        this.board[y][x] = player;
        this.emptyFields--;

        return true;
      }
    } else {
      return false;
    }
  }

  /**
   * Checks if the game is finished. Also sets the board's winner. The game is finished if all
   * spaces are taken or if there are three spaces in a row taken by the same player.
   *
   * @return boolean whether the game is finished
   */
  public boolean isFinished() {
    if (this.emptyFields == 0) {
      return true;
    }
    // 3 in a row horizontally
    if (this.board[0][0] != -1 && this.board[0][0] == this.board[0][1]
        && this.board[0][0] == this.board[0][2]) {
      this.winner = this.board[0][0];
      return true;
    } else if (this.board[1][0] != -1 && this.board[1][0] == this.board[1][1]
        && this.board[1][0] == this.board[1][2]) {
      this.winner = this.board[1][0];
      return true;
    } else if (this.board[2][0] != -1 && this.board[2][0] == this.board[2][1]
        && this.board[2][0] == this.board[2][2]) {
      this.winner = this.board[2][0];
      return true;
      // 3 in a row vertically
    } else if (this.board[0][0] != -1 && this.board[0][0] == this.board[1][0]
        && this.board[0][0] == this.board[2][0]) {
      this.winner = this.board[0][0];
      return true;
    } else if (this.board[0][1] != -1 && this.board[0][1] == this.board[1][1]
        && this.board[0][1] == this.board[2][1]) {
      this.winner = this.board[0][1];
      return true;
    } else if (this.board[0][2] != -1 && this.board[0][2] == this.board[1][2]
        && this.board[0][2] == this.board[2][2]) {
      this.winner = this.board[0][2];
      return true;
      // 3 in a row diagonally
    } else if (this.board[0][0] != -1 && this.board[0][0] == this.board[1][1]
        && this.board[0][0] == this.board[2][2]) {
      this.winner = this.board[0][0];
      return true;
    } else if (this.board[0][2] != -1 && this.board[0][2] == this.board[1][1]
        && this.board[0][2] == this.board[2][0]) {
      this.winner = this.board[0][2];
      return true;
    } else {
      return false;
    }
  }
}