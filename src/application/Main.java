package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JDK 11, Eclipse 2019-06 https://gluonhq.com/products/javafx/
 * https://openjfx.io/openjfx-docs/
 *
 * @author Debra Deppeler
 */
public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;
	private static final int WINDOW_WIDTH = 300;
	private static final int WINDOW_HEIGHT = 200;
	private static final String APP_TITLE = "Network Visualizer";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Visualization HBox
		ArrayList<String> friends = new ArrayList<String>();
		for(int i = 0;i<10;i++) {
			friends.add("user"+(i+1));
		}
		VBox vboxVis = getVisualizerBox("user0",friends);
		// Control HBox
		HBox hboxControl = getControlHBox(true); //This boolean is whether a centralUser is selected
		// Main layout is Border Pane example (top,left,center,right,bottom)
		BorderPane root = new BorderPane();

		//Add primary boxes to root
		root.setTop(vboxVis);
		root.setBottom(hboxControl);
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		//Set primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}
	private HBox getControlHBox(boolean cUser) {
		HBoxControl control = new HBoxControl(cUser);
		return control.getHBoxControl();
	}
	private VBox getVisualizerBox(String cUser, List<String> friends) {
		VBoxVisualizer visualizer = new VBoxVisualizer(cUser, friends);
		return visualizer.getVBoxVisualizer();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
