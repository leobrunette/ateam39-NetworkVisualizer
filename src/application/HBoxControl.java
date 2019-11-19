package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HBoxControl{
	HBox primary;
	
////Left portion////
	VBox vboxLeft;
  //Central user
	HBox hboxCentralUser;
	Label lCentralUser;
	TextField tfCentralUser;
	Button bCentralUser;
  //Import network
	HBox hboxImportNetwork;
	Label lImportNetwork;
	TextField tfImportNetwork;
	Button bImportNetwork;
  //Export network
	HBox hboxExportNetwork;
	Label lExportNetwork;
	Button bExportNetwork;
  //Clear network
	HBox hboxClearNetwork;
	Label lClearNetwork;
	Button bClearNetwork;
	
////Right portion////
	VBox vboxRight;
  //Undo and redo buttons
	HBox hboxUndoRedo;
	Button bUndo;
	Button bRedo;
  //Add friend
	HBox hboxAddFriend;
	Label lAddFriend;
	TextField tfAddFriend;
	Button bAddFriend;
  //Remove friend
	HBox hboxRemoveFriend;
	Label lRemoveFriend;
	TextField tfRemoveFriend;
	Button bRemoveFriend;
	
	public HBoxControl() {
		primary = new HBox();
		
////////Left portion////////	
		vboxLeft = new VBox();
	////Central user
		hboxCentralUser = new HBox();
		lCentralUser = new Label("central user:");
		tfCentralUser = new TextField();
		bCentralUser = new Button("edit");
	////Import network
		hboxImportNetwork = new HBox();
		lImportNetwork = new Label("import network:");
		tfImportNetwork = new TextField();
		bImportNetwork = new Button("import");
	////Export network
		hboxExportNetwork = new HBox();
		lExportNetwork = new Label("export network:");
		bExportNetwork = new Button("export");
	////Clear network
		hboxClearNetwork = new HBox();
		lClearNetwork = new Label("clear network:");
		bClearNetwork = new Button("clear");
		
////////Right portion////////
		vboxRight = new VBox();
	////Undo and redo buttons
		hboxUndoRedo = new HBox();
		bUndo = new Button("undo");
		bRedo = new Button("redo");
	////Add friend
		hboxAddFriend = new HBox();
		lAddFriend = new Label("add friend:");
		tfAddFriend = new TextField();
		bAddFriend = new Button("add");
	////Remove friend
		hboxRemoveFriend = new HBox();
		lRemoveFriend = new Label("remove friend:");
		tfRemoveFriend = new TextField();
		bRemoveFriend = new Button("remove");
		
		setAttributes();
		collapseContainers();
	}
	private void setAttributes() {
		tfCentralUser.setPromptText("username");
		tfImportNetwork.setPromptText("filename");
		hboxUndoRedo.setSpacing(100);
		tfAddFriend.setPromptText("username");
		tfRemoveFriend.setPromptText("username");
		primary.setPadding(new Insets(50,50,50,50));
		primary.setSpacing(50);
		vboxLeft.setSpacing(20);
		vboxRight.setSpacing(30);
	}
	private void collapseContainers() {
		hboxCentralUser.getChildren().addAll(lCentralUser,tfCentralUser,bCentralUser);
		hboxImportNetwork.getChildren().addAll(lImportNetwork,tfImportNetwork,bImportNetwork);
		hboxExportNetwork.getChildren().addAll(lExportNetwork,bExportNetwork);
		hboxClearNetwork.getChildren().addAll(lClearNetwork,bClearNetwork);
		vboxLeft.getChildren().addAll(hboxCentralUser,hboxImportNetwork, hboxExportNetwork, hboxClearNetwork);
		hboxUndoRedo.getChildren().addAll(bUndo,bRedo);
		hboxAddFriend.getChildren().addAll(lAddFriend,tfAddFriend,bAddFriend);
		hboxRemoveFriend.getChildren().addAll(lRemoveFriend,tfRemoveFriend,bRemoveFriend);
		vboxRight.getChildren().addAll(hboxUndoRedo,hboxAddFriend,hboxRemoveFriend);
		primary.getChildren().addAll(vboxLeft,vboxRight);
	}
	public HBox getHBoxControl() {
		return primary;
	}
}
