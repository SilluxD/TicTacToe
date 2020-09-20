import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage stage) {
    try {
      Parent root = FXMLLoader.load(getClass().getResource("gameView.fxml"));
      Scene scene = new Scene(root, 267, 275);

      stage.setTitle("Tic-Tac-Toe");
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
    launch(args);
  }
}


