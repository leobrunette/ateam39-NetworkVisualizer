package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
	}

	public void saveToFile(File file) {
		try {
			PrintWriter writer = new PrintWriter(file.getPath(), "UTF-8");
			for (String line : commands) {
				writer.println(line);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// show error in control pane
			System.out.println("file not found");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public boolean addFriends(String friend1, String friend2) {
		if (friend1.equals(friend2)) {
			return false;
		}
		if (graph.addEdge(friend1, friend2)) {
			commands.add("a " + friend1 + " " + friend2);
			return true;
		}
		return false;
	}

	public boolean removeFriends(String friend1, String friend2) {
		if (friend1.equals(friend2)) {
			return false;
		}
		if (graph.removeEdge(friend1, friend2)) {
			commands.add("r " + friend1 + " " + friend2);
			return true;
		}
		return false;
	}

	public boolean addUser(String friend) {
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

	public boolean removeUser(String friend) {
		if (friend != null) {
			if (graph.removeVertex(friend)) {
				commands.add("r " + friend);
				return true;
			}
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
		commands.add("s " + centralUser);
	}
}
