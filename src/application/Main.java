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
import javafx.stage.Stage;

/**
 * JDK 11, Eclipse 2019-06 https://gluonhq.com/products/javafx/
 * https://openjfx.io/openjfx-docs/
 *
 * @author ateam39
 */
public class Main extends Application {
	private static final int WINDOW_WIDTH = 300;
	private static final int WINDOW_HEIGHT = 200;
	private static final String APP_TITLE = "Network Visualizer";
	private Model model;
	private Pane vis;
	private Pane control;
	private BorderPane root;
	private Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Main layout
		root = new BorderPane();
		// Set scene
		scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// Set primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		// Model Generation for milestone 1
		ObservableList<String> friends = FXCollections.observableArrayList();
		for (int i = 0; i <= 20; i++) {
			friends.add("user" + i);
		}
		primaryStage.show();
		model = new Model(primaryStage, 0, friends, (int) scene.getWidth(), (int) scene.getHeight());
		// VisualizerPane
		vis = new VisualizerPane(model);
		// ControlPane
		control = new ControlPane(model); // Set central user to -999 if not selected

		// Add primary boxes to root
		root.setTop(vis);
		root.setBottom(control);
		// Show stage
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
