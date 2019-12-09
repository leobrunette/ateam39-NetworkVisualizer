package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
		//// Export network
		hboxExportNetwork = new InputControl("export network:", "", "export", "file path", disabled, controller);
		//// Clear network
		hboxClearNetwork = new InputControl("clear network:", null, "clear", null, disabled, controller);
////////Right portion////////
		vboxRight = new VBox();
		//// Add friend
		hboxAddFriend = new InputControl("add friend:", "", "add", "username", disabled, controller);
		//// Remove friend
		hboxRemoveFriend = new InputControl("remove friend:", "", "remove", "username", disabled, controller);
		this.setMinHeight(1 * (controller.getWindowHeight() / 3));
		this.setMaxHeight(2*(controller.getWindowHeight()/5));
		setAttributes();
		collapseContainers();
	}

	private void setAttributes() {
		this.getStyleClass().add("controlPane");
		vboxLeft.setSpacing(50);
		vboxRight.setSpacing(50);
	}

	private void collapseContainers() {
		vboxLeft.getChildren().addAll(hboxCentralUser, hboxImportNetwork, hboxExportNetwork);
		vboxRight.getChildren().addAll(hboxAddFriend, hboxRemoveFriend,hboxClearNetwork);
		this.getChildren().addAll(vboxLeft, vboxRight);
	}
}
