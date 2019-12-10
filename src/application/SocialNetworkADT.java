package application;

import java.io.File;
import java.util.List;

public interface SocialNetworkADT {


      public void loadFromNetwork(File file);

      public void saveToFile(File file);

      public boolean addFriends(String friend1, String friend2);
          

      public boolean removeFriends(String friend1, String friend2);

      public boolean addUser(String friend);

      public boolean removeUser(String friend);

      public List<String> getFriends(String user);
      
      public int getNumberOfUsers();

      public String getCentralUser();

      public void setCentralUser(String centralUser);
}
