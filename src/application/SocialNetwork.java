package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class SocialNetwork {
  Graph graph = new Graph();
private String centralUser;
  public SocialNetwork() {
	  
  }
  
  public void loadFromNetwork(String filepath) {
	  File file = new File(filepath);
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
  public boolean addFriends(String friend1, String friend2){
    if (friend1.equals(friend2)) {
      return false;
    }
    graph.addEdge(friend1, friend2);
    return true;
  }
  
  public boolean removeFriends(String friend1, String friend2) {
    if (friend1.equals(friend2)) {
      return false;
    }
    graph.removeEdge(friend1, friend2);
    return true;
  }
  
  public boolean addUser(String friend) {
    if (friend != null) {
      if (graph.order() == 0) {
      graph.addVertex(friend);
      this.centralUser = friend;
      }
      else {
        graph.addVertex(friend);
      }
      return true;
    }
    return false;
  }
  
  public List<String> getFriends(String user){
   return  graph.getAdjacentVerticesOf(user);
  }
  
}
