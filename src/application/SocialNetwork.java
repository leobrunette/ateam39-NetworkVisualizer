package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * SocialNetwork.java
 * Class the contains and interacts with the graph that contains the users
 *
 * @author ateam39
 */

public class SocialNetwork {
	private Graph graph;
	private String centralUser;
	private ArrayList<String> commands;

	public SocialNetwork() {
		graph = new Graph();
		centralUser = null;
		commands = new ArrayList<String>();
	}

	public void loadFromNetwork(File file) {//parses the file and calls the commands given by the file
		Scanner in = null;
		try {
			in = new Scanner(file);
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] partsOfLine = line.split(" ");
				if (partsOfLine[0].equals("a")) {
					if (partsOfLine.length == 2) {
						addUser(partsOfLine[1]);
					} else {
						addFriends(partsOfLine[1], partsOfLine[2]);
					}
				} else if (partsOfLine[0].equals("r")) {
					if (partsOfLine.length == 2) {
						removeUser(partsOfLine[1]);
					} else {
						removeFriends(partsOfLine[1], partsOfLine[2]);
					}
				} else if (partsOfLine[0].equals("s")) {
					setCentralUser(partsOfLine[1]);
				}
			}
		} catch (FileNotFoundException e) {
			Alert importNetworkError = new Alert(AlertType.ERROR, "Import Network from File: file not found.");
			importNetworkError.showAndWait().filter(r -> r == ButtonType.OK);
		}
	}

	public void saveToFile(File file) {//saves to file using the saved commands
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			PrintWriter writer = new PrintWriter(file.getPath(), "UTF-8");
			for (String line : commands) {
				writer.println(line);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			Alert exportNetworkError = new Alert(AlertType.ERROR, "Save Network to File: invalid path.");
			exportNetworkError.showAndWait().filter(r -> r == ButtonType.OK);
		} catch (UnsupportedEncodingException e) {
			Alert exportNetworkError = new Alert(AlertType.ERROR, "Save Network to File: unsupported encoding.");
			exportNetworkError.showAndWait().filter(r -> r == ButtonType.OK);
		} catch (IOException e) {
			Alert exportNetworkError = new Alert(AlertType.ERROR, "Save Network to File: IO Exception.");
			exportNetworkError.showAndWait().filter(r -> r == ButtonType.OK);
		}
	}

	public boolean addFriends(String friend1, String friend2) {//adds edge to graph and adds command
		if (friend1.equals(friend2)) {
			return false;
		} else {
			if (graph.addEdge(friend1, friend2)) {
				commands.add("a " + friend1 + " " + friend2);
				return true;
			}
			return false;
		}
	}

	public boolean removeFriends(String friend1, String friend2) {//removes edge to graph and adds command
		if (friend1.equals(friend2)) {
			return false;
		} else {
			if (graph.removeEdge(friend1, friend2)) {
				commands.add("r " + friend1 + " " + friend2);
				return true;
			}
			return false;
		}
	}

	public boolean addUser(String friend) {//adds vertex and adds command
		if (friend != null) {
			if (graph.addVertex(friend)) {
				commands.add("a " + friend);
				if (graph.order() == 1) {
					this.centralUser = friend;
					commands.add("s " + friend);
				}
				return true;
			}
		}
		return false;
	}

	public boolean removeUser(String friend) {//removes vertex and adds command
		if (friend != null) {
			if (graph.removeVertex(friend)) {
				commands.add("r " + friend);
				return true;
			}
		}
		return false;
	}

	public List<String> getFriends(String user) {//gets friends of users
		return graph.getAdjacentVerticesOf(user);
	}

	public int getNumberOfUsers() {//returns the number of vertices in graph
		return graph.order();
	}

	public String getCentralUser() {//returns central user
		return centralUser;
	}

	public void setCentralUser(String centralUser) {//sets central user and adds command
		if(addUser(centralUser)) {
			commands.add("a "+centralUser);
		}
		commands.add("s "+centralUser);
		this.centralUser = centralUser;
	}
}
