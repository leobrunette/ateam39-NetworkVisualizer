package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Main.java
 * Starts the JavaFX application
 *
 * @author ateam39
 */
public class Main extends Application {
	private static final String APP_TITLE = "Network Visualizer";
	private BorderPane root;
	private Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle(APP_TITLE);
		primaryStage.show();
		primaryStage.setMaximized(true); //Maximizing application to allow for space for graphics
		Controller controller = new Controller(primaryStage); //The controller is a way to organize our methods called by all the buttons in the program
		controller.generateStage();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
