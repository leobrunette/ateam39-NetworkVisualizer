package application;

import java.io.File;
import java.io.FileNotFoundException;
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
  
}
