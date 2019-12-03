package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * JDK 11, Eclipse 2019-06 https://gluonhq.com/products/javafx/
 * https://openjfx.io/openjfx-docs/
 *
 * @author ateam39
 */
public class Main extends Application {
	private static final String APP_TITLE = "Network Visualizer";
	private ViewModel model;
	private Pane vis;
	private Pane control;
	private BorderPane root;
	private Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Controller controller = new Controller();
		root = new BorderPane();
		scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Network Visualizer");
		// Show stage
		controller.generateStage(new ViewModel(primaryStage, "user0", controller.getFriendsOfUser("user0"), (int) primaryStage.getWidth(), (int) primaryStage.getHeight(), controller));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
