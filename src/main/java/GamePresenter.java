import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GamePresenter {

  private Game game;
  private boolean aiActive = false;

  @FXML
  private Text textField;
  @FXML
  private ToggleButton aiToggle;
  @FXML
  private Button ngButton;

  @FXML
  private ImageView f00;
  @FXML
  private ImageView f01;
  @FXML
  private ImageView f02;
  @FXML
  private ImageView f10;
  @FXML
  private ImageView f11;
  @FXML
  private ImageView f12;
  @FXML
  private ImageView f20;
  @FXML
  private ImageView f21;
  @FXML
  private ImageView f22;
  private ImageView[][] images;


  /**
   * Initializes the presenter. The images are are set with default values and clicking them is
   * connected to a function.
   */
  @FXML
  public void initialize() {
    this.images = new ImageView[][]{
        {f00, f01, f02},
        {f10, f11, f12},
        {f20, f21, f22}
    };
    for (ImageView[] i : images) {
      for (ImageView j : i) {
        j.setImage(new Image("w.png"));
        j.setOnMousePressed(this::onPress);
      }
    }
    this.textField.setText("");
    this.game = new Game(this);
  }

  /**
   * Called then the toggle-button is pressed. he aiActive status is (re)-set and a new game is
   * created.
   */
  public void toggleAi() {
    this.aiActive = !this.aiActive;
    this.newGame();
  }

  /**
   * Create a new game presented by this presenter. If the ai-toggle is set, the ai will be
   * activated in the game aswell.
   */
  public void newGame() {
    this.game = new Game(this);
    this.resetImages();
    if (this.aiActive) {
      this.game.activateAi();
    }
    this.textField.setText("");
  }

  /**
   * When clicking an image the coordinates are determined and the click function in the game is
   * called.
   *
   * @param event click on an image
   */
  private void onPress(javafx.scene.input.MouseEvent event) {
    int x = GridPane.getColumnIndex((Node) event.getSource());
    int y = GridPane.getRowIndex((Node) event.getSource());

    this.game.playerClickField(x, y);
  }

  public void showText(String text) {
    this.textField.setText(text);
  }

  /**
   * Reset all images to default values.
   */
  private void resetImages() {
    for (ImageView[] i : images) {
      for (ImageView j : i) {
        j.setImage(new Image("w.png"));
      }
    }
  }

  /**
   * Calls the function to load the fitting images for the fields on the board.
   *
   * @param board the board which should be shown.
   */
  public void loadImages(int[][] board) {
    for (int x = 0; x < board.length; x++) {
      for (int y = 0; y < board[x].length; y++) {
        this.loadImage(x, y, board[y][x]);
      }
    }
  }

  /**
   * Loads the correct image for each value on the board.
   *
   * @param x     X-coordinate of the field on the board
   * @param y     Y-coordinate of the field on the board
   * @param value the value of the field on the board
   */
  private void loadImage(int x, int y, int value) {
    String imgUrl = getImgUrlForField(value);
    this.images[x][y]
        .setImage(new Image(imgUrl));
  }

  /**
   * Get fitting image-url for a value.
   *
   * @param value the value of a field on the board
   * @return String of the image-url
   */
  private String getImgUrlForField(int value) {
    if (value == 0) {
      return "x.png";
    } else if (value == 1) {
      return "o.png";
    } else {
      return "w.png";
    }
  }
}