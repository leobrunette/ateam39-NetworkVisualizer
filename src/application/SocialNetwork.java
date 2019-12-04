package application;

import java.io.File;
import java.io.FileNotFoundException;
<<<<<<< HEAD
import java.util.Scanner;

public class SocialNetwork {
  Graph graph = new Graph();
  
  public boolean addFriends(String nameOne, String nameTwo) {
     return graph.addEdge(nameOne, nameTwo);
  }
  
  public void loadFromFile(File filename) {
    Scanner in = null;
    try {
       in = new Scanner(filename);
    } catch (FileNotFoundException e) {
      
    }
    while (in.hasNextLine()) {
      String line = in.nextLine();
      String[] partsOfLine = line.split("");
      if (partsOfLine[0].equals("a")) {
        if (partsOfLine.length == 2) {
          graph.addVertex(partsOfLine[1]);
        }
        else {
        graph.addEdge(partsOfLine[1], partsOfLine[2]);
        }
      }
      else if (partsOfLine[0].equals("r")) {
        if (partsOfLine.length == 2) {
          graph.removeVertex(partsOfLine[1]);
        }
        else {
        graph.removeEdge(partsOfLine[1], partsOfLine[2]);
        }
      }
      else if (partsOfLine[0].equals("s")) {
        
      }
    }
  }
=======
import java.util.List;
import java.util.Scanner;


public class SocialNetwork {
private String centralUser;
private Graph network;
SocialNetwork(){
  network = new Graph();
}
  public boolean addFriends(String friend1, String friend2){
    if (friend1.equals(friend2)) {
      return false;
    }
    network.addEdge(friend1, friend2);
    return true;
  }
  
  public boolean removeFriends(String friend1, String friend2) {
    if (friend1.equals(friend2)) {
      return false;
    }
    network.removeEdge(friend1, friend2);
    return true;
  }
  
  public boolean addUser(String friend) {
    if (friend != null) {
      if (network.order == 0) {
      network.addVertex(friend);
      this.centralUser = friend;
      }
      else {
        network.addVertex(friend);
      }
      return true;
    }
    return false;
  }
  
  public List<String> getFriends(String user){
   return  network.getAdjacentVerticesOf(user);
  }
  
>>>>>>> dev
}
