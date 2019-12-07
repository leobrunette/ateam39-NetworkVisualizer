package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlPane extends HBox {
////Left portion////
	VBox vboxLeft;
	// Central user
	HBox hboxCentralUser;
	// Import network
	HBox hboxImportNetwork;
	Label importNetworkException;
	// Export network
	HBox hboxExportNetwork;
	// Clear network
	HBox hboxClearNetwork;

////Right portion////
	VBox vboxRight;
	// Undo and redo buttons
	HBox hboxUndoRedo;
	Button bUndo;
	Button bRedo;
	// Add friend
	HBox hboxAddFriend;
	// Remove friend
	HBox hboxRemoveFriend;

	public ControlPane(Controller controller) {
		//// Check if central user selected / no network
		boolean disabled = false;
		if (controller.isNetworkEmpty()) {
			disabled = true;
		}
////////Left portion////////	
		vboxLeft = new VBox();
		//// Central user
		if (!disabled) {
			hboxCentralUser = new InputControl("central user:", "", "set", "username", false, controller);
		} else {
			hboxCentralUser = new InputControl("central user:", "", "add user", "username", false, controller);
		}
		//// Import network
		hboxImportNetwork = new InputControl("import network:", "", "import", "file path", false, controller);
		importNetworkException = new Label("error");
		importNetworkException.setVisible(false);
		importNetworkException.getStyleClass().addAll("exception");
		//// Export network
		hboxExportNetwork = new InputControl("export network:", "", "export", "file path", disabled, controller);
		//// Clear network
		hboxClearNetwork = new InputControl("clear network:", null, "clear", null, disabled, controller);
////////Right portion////////
		vboxRight = new VBox();
		//// Undo and redo buttons
		hboxUndoRedo = new HBox();
		bUndo = new Button("undo");
		bUndo.setDisable(disabled);
		bUndo.getStyleClass().addAll("butInput", "red");
		bRedo = new Button("redo");
		bRedo.setDisable(disabled);
		bRedo.getStyleClass().add("butInput");
		//// Add friend
		hboxAddFriend = new InputControl("add friend:", "", "add", "username", disabled, controller);
		//// Remove friend
		hboxRemoveFriend = new InputControl("remove friend:", "", "remove", "username", disabled, controller);
		this.setMinHeight(1 * (controller.getWindowHeight() / 3));
		// this.setMaxHeight(2*(model.getWindowHeight()/5));
		setAttributes();
		collapseContainers();
	}

	private void setAttributes() {
		hboxUndoRedo.getStyleClass().add("undoRedo");
		this.getStyleClass().add("controlPane");
		vboxLeft.setSpacing(40);
		vboxRight.setSpacing(60);
	}

	private void collapseContainers() {
		vboxLeft.getChildren().addAll(hboxCentralUser, hboxImportNetwork, importNetworkException, hboxExportNetwork,
				hboxClearNetwork);
		hboxUndoRedo.getChildren().addAll(bUndo, bRedo);
		vboxRight.getChildren().addAll(hboxUndoRedo, hboxAddFriend, hboxRemoveFriend);
		this.getChildren().addAll(vboxLeft, vboxRight);
	}
}
