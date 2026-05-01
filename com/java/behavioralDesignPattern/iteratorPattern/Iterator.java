package com.java.behavioralDesignPattern.iteratorPattern;

import java.util.ArrayList;
import java.util.List;

/*
Iterator Pattern
The Iterator Pattern is a behavioral design pattern that provides a way to access the elements of a collection
sequentially without exposing the underlying representation.

Formal Definition
The Iterator Pattern is a behavioral design pattern that entrusts the traversal behavior of a collection to a
separate design object. It traverses the elements without exposing the underlying operations.

This means whether your collection is an array, a list, a tree, or something custom, you can use an iterator to
traverse it in a consistent manner, one element at a time, without worrying about how the data is stored or managed
internally.

Real-Life Analogy
Think of a vending machine. You don’t need to know how the snacks are arranged inside or where exactly your favorite
drink is stored. You just press the "Next" button to scroll through options one by one. The vending machine controls
the order and pace of traversal.

Similarly, an iterator acts like that "Next" button, giving you one item at a time, hiding the complexity of what’s
going on behind the scenes.
*/
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

interface VideoIterator {
    boolean hasNext();
    Video next();
}

class YoutubeVideoIterator implements VideoIterator {
    int currentIndex = 0;
    List<Video> videoList;

    YoutubeVideoIterator(List<Video> videos) {
        this.videoList = videos;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < videoList.size();
    }

    @Override
    public Video next() {
        return hasNext() ? videoList.get(currentIndex++) : null;
    }
}

public class Iterator {
    public static void main(String args[]) {
        YouTubePlaylist youTubePlaylist = new YouTubePlaylist();
        youTubePlaylist.addVideo(new Video("LLD playlist"));
        youTubePlaylist.addVideo(new Video("Spring boot basics"));
        youTubePlaylist.addVideo(new Video("0 to 1 DSA"));

        YoutubeVideoIterator youtubeVideoIterator = new YoutubeVideoIterator(youTubePlaylist.getPlayList());

        while(youtubeVideoIterator.hasNext()) {
            System.out.println(youtubeVideoIterator.next().getTitle());
        }
    }
}
/*
How This Solves the Problem
With the iterator pattern in place, we’ve clearly separated the concern of how elements are traversed from the actual
data structure that stores them. Here's how this improves our design:
Problem:	How Iterator Pattern Solves It
Direct access to internal data structure:	The collection no longer exposes its internal data (like a list or array)
directly for traversal. Instead, an iterator is used to access elements one-by-one, encapsulating the structure.
No standard way to iterate:	All traversal is now handled through a consistent interface (hasNext() / next()),
regardless of how the data is stored internally. This ensures uniformity in how iteration happens.
Traversal logic spread across client code:	The logic for maintaining iteration state (e.g., index or position)
is encapsulated within the iterator class itself, keeping the client code clean and focused only on usage.
Difficult to customize traversal:	Custom iterator classes can easily be extended to provide different traversal
strategies (e.g., reverse, filtering, skipping), without changing the underlying collection.
Tight coupling to collection type:	Client code no longer depends on the exact type of data structure (like array,
list, vector). It interacts only with the iterator, reducing dependencies and improving flexibility.
 */