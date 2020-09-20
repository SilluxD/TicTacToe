import java.util.Random;

public class Game {

  private Board board;
  private GamePresenter gamePresenter;
  private int player = 0;


  private boolean aiActive = false;
  private AiPlayer aiPlayer;

  /**
   * Constructor for this Game-Object. A new Board is created and the presenter is set.
   *
   * @param presenter GamePresenter presenting this game
   */
  public Game(GamePresenter presenter) {
    this.board = new Board();
    this.gamePresenter = presenter;
  }

  /**
   * Activate the ai by setting the aiActive flag and creating a new AiPlayer. In 50% of the time,
   * this ai-player will make the first move.
   */
  public void activateAi() {
    this.aiActive = true;
    this.aiPlayer = new AiPlayer(this, this.player);

    // ai starts the game
    if (new Random().nextBoolean()) {
      this.aiPlayer.makeMove();
    }
  }

  public int[][] getBoard() {
    return this.board.getBoard();
  }

  /**
   * Called when the ai-player requests to click a field. The current player will be changed and the
   * game-presenter will be updated.
   *
   * @param x X-coordinate of the field
   * @param y Y-coordinate of the field
   */
  public void aiClickField(int x, int y) {
    if (this.board.clickField(x, y, this.player)) {
      // change player
      this.player = (this.player + 1) % 2;
      this.updateGamePresenter();
    }
  }

  /**
   * Called when a human player requests to click a field. If this action is successful, the current
   * player will be changed and the game-presenter will be updated. Furthermore if the ai is
   * enabled, the ai player will be asked to make a move.
   *
   * @param x X-coordinate of the field
   * @param y Y-coordinate of the field
   */
  public void playerClickField(int x, int y) {
    if (this.board.clickField(x, y, this.player)) {
      this.player = (this.player + 1) % 2;
      if (this.aiActive && !this.board.isFinished()) {
        this.aiPlayer.makeMove();
      }
      this.updateGamePresenter();
    }
  }

  /**
   * Calls to update the game-presenter. Collects all information to send it to the presenter.
   */
  private void updateGamePresenter() {
    this.gamePresenter.loadImages(this.board.getBoard());
    if (this.board.isFinished()) {
      int winner = this.board.getWinner();
      String text = winner == -1 ? "The game ended in a draw."
          : String.format("Player %s has won the game.", winner + 1);
      this.gamePresenter.showText(text);
    }
  }
}