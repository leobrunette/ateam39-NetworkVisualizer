package defaultpackage;

import java.io.File;
import java.io.FileNotFoundException;
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
}
