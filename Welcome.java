package sabotage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Welcome extends Application {
	
	public void start(Stage stage) {
		BorderPane pane = new BorderPane();
		pane.setTop(new CustomPane("S A B O T A G E"));
		pane.setRight(new CustomPane("   "));
		pane.setBottom(new CustomPane("  "));
		pane.setLeft(new CustomPane(" "));
		String welcome = "W E L C O M E   TO   S A B O T A G E";
		double rotation = 90;
		double offset = pane.getPrefWidth() / 2;
		double radius = 100;
		double x = offset + Math.cos(rotation) * radius;
		double y = offset + Math.sin(rotation) * radius;
		for (int i = 0; i < welcome.length(); i++) {
			x = offset + Math.cos(Math.toRadians(rotation)) * radius;
			y = offset + Math.sin(Math.toRadians(rotation)) * radius;
		}
		pane.setCenter(new CustomPane(welcome));
		
		Scene scene = new Scene(pane, 700, 550);
		stage.setTitle("Sabotage - Welcome");
		stage.setScene(scene);
		stage.show();
	}
}

class CustomPane extends StackPane {
	public CustomPane(String title) {
		getChildren().add(new Label(title));
		setStyle("-fx-border-color: green");
		setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
