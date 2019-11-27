package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlPane extends HBox{
////Left portion////
	VBox vboxLeft;
  //Central user
	HBox hboxCentralUser;
  //Import network
	HBox hboxImportNetwork;
  //Export network
	HBox hboxExportNetwork;
  //Clear network
	HBox hboxClearNetwork;
	
////Right portion////
	VBox vboxRight;
  //Undo and redo buttons
	HBox hboxUndoRedo;
	Button bUndo;
	Button bRedo;
  //Add friend
	HBox hboxAddFriend;
  //Remove friend
	HBox hboxRemoveFriend;
	
	public ControlPane(Model model) {
////////Left portion////////	
		vboxLeft = new VBox();
	////Central user
		hboxCentralUser = new InputControl("central user:", "", "edit", "username");
	////Import network
		hboxImportNetwork = new InputControl("import network:","","import", "filename");
	////Export network
		hboxExportNetwork = new InputControl("export network:",null,"export", null);
	////Clear network
		hboxClearNetwork = new InputControl("clear network:",null,"clear", null);
////////Right portion////////
		vboxRight = new VBox();
	////Undo and redo buttons
		hboxUndoRedo = new HBox();
		bUndo = new Button("undo");
		bRedo = new Button("redo");
	////Add friend
		hboxAddFriend = new InputControl("add friend:","","add","username");
	////Remove friend
		hboxRemoveFriend = new InputControl("remove friend:","","remove","username");
		
		setAttributes();
		collapseContainers();
	}
	private void setAttributes() {
		hboxUndoRedo.setSpacing(100);
		this.setPadding(new Insets(50,50,50,50));
		this.setSpacing(50);
		vboxLeft.setSpacing(20);
		vboxRight.setSpacing(30);
	}
	private void collapseContainers() {
		vboxLeft.getChildren().addAll(hboxCentralUser,hboxImportNetwork, hboxExportNetwork, hboxClearNetwork);
		hboxUndoRedo.getChildren().addAll(bUndo,bRedo);
		vboxRight.getChildren().addAll(hboxUndoRedo,hboxAddFriend,hboxRemoveFriend);
		this.getChildren().addAll(vboxLeft,vboxRight);
	}
}
