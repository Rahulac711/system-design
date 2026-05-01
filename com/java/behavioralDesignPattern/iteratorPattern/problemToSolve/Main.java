package com.java.behavioralDesignPattern.iteratorPattern.problemToSolve;

import java.util.ArrayList;
import java.util.List;

class Video {
    String title;
    Video(String title) {
        this.title = title;
    }
    String getTitle() {
        return title;
    }
}

class YouTubePlaylist {
    private List<Video> playList = new ArrayList<>();

    void addVideo(Video video) {
        playList.add(video);
    }

    List<Video> getPlayList() {
        return playList;
    }
}

public class Main {
    public static void main(String args[]) {
        YouTubePlaylist playlist = new YouTubePlaylist();
        playlist.addVideo(new Video("LLD Tutorial"));
        playlist.addVideo(new Video("System Design Basics"));

        // Loop through videos and print titles
        for (Video v : playlist.getPlayList()) {
            System.out.println(v.getTitle());
        }
    }

}
/*
What are the Issues?
While the code works, there are several design-level concerns:
Exposes internal structure:
The internal list or array is directly returned via getVideos() or similar methods.
This breaks encapsulation, as clients can access or even modify the internal collection outside the owning class.
Tight coupling with underlying structure:
The external code is tightly bound to the specific type of collection used (like vector, list, etc.).
Any change in the internal structure may require changes in client code.
No control over traversal
Traversal logic is managed outside the class.
You can't enforce custom traversal behaviors (e.g., reverse, skip elements, filter) without modifying external code.
Difficult to support multiple independent traversals:
If two parts of your program want to iterate over the same playlist independently, there's no built-in way to do that cleanly.
You have to manage indexing and traversal state manually.

 */