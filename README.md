# ateam39-NetworkVisualizer
GUI is completed and styled, however changes can be made easily if desired.
For functionality, there is a branch called functionallity, probably branch off of that with another branch called your name or what specifically you are working on.
Also I think we should probably used a "controller" class to hold methods for chaning the central user that our buttons access, like they'll pass the new name of the central user. This will help with organization and in this "controller" is where we can also interact with the Graph perhaps.
Another Note: we may also need to add the graph to the model passed between the classes for the GUI because I don't see a lot of other options because a new controller will be instantiated with both the visualizer and controller class and we won't want to have to re-read the text file every single time.
