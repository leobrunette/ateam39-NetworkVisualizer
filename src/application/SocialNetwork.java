package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocialNetwork {
	private Graph graph;
	private String centralUser;
	private ArrayList<String> commands;

	public SocialNetwork() {
		graph = new Graph();
		centralUser = null;
		commands = new ArrayList<String>();
	}

	public void loadFromNetwork(File file) {
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {

		}
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] partsOfLine = line.split(" ");
			if (partsOfLine[0].equals("a")) {
				if (partsOfLine.length == 2) {
					graph.addVertex(partsOfLine[1]);
				} else {
					graph.addEdge(partsOfLine[1], partsOfLine[2]);
				}
			} else if (partsOfLine[0].equals("r")) {
				if (partsOfLine.length == 2) {
					graph.removeVertex(partsOfLine[1]);
				} else {
					graph.removeEdge(partsOfLine[1], partsOfLine[2]);
				}
			} else if (partsOfLine[0].equals("s")) {
				centralUser = partsOfLine[1];
			}
		}
	}

	public void saveToFile(File file) {

	}

	public boolean addFriends(String friend1, String friend2) {
		if (friend1.equals(friend2)) {
			return false;
		}
		return graph.addEdge(friend1, friend2);
	}

	public boolean removeFriends(String friend1, String friend2) {
		if (friend1.equals(friend2)) {
			return false;
		}
		return graph.removeEdge(friend1, friend2);
	}

	public boolean addUser(String friend) {
		if (friend != null) {
			if (graph.order() == 0) {
				if (graph.addVertex(friend)) {
					this.centralUser = friend;
					return true;
				}
			} else {
				return graph.addVertex(friend);
			}
		}
		return false;
	}

	public boolean removeUser(String friend) {
		if (friend != null) {
			return graph.removeVertex(friend);
		}
		return false;
	}

	public List<String> getFriends(String user) {
		return graph.getAdjacentVerticesOf(user);
	}

	public int getNumberOfUsers() {
		return graph.order();
	}

	public String getCentralUser() {
		return centralUser;
	}

	public void setCentralUser(String centralUser) {
		this.centralUser = centralUser;
	}
}
