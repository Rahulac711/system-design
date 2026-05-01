package com.java.behavioralDesignPattern.observerPattern;

import java.util.ArrayList;
import java.util.List;


/*
Real-Life Analogy
Think of subscribing to a YouTube channel. Once you hit the Subscribe button and turn on notifications, you don’t
have to keep visiting the channel to check for new videos. As soon as a new video is uploaded, you get notified
instantly.
In this case:
The channel is the subject.
The subscribers are the observers.
The notification is the automatic update mechanism triggered by the subject.

Similarly, in software, when an object (subject) undergoes a change, all registered observers get notified, just
like YouTube alerts its subscribers.
 */
interface Subscriber {
    void update(String videoTitle);
}

class EmailSubscriber implements Subscriber {

    String name;
    EmailSubscriber(String name) {
        this.name = name;
    }

    public void update(String videoTitle) {
        System.out.println("[Email "+name+"] Channel has uploaded video "+videoTitle);
    }
}

class MobileSubscriber implements Subscriber {
    String name;

    MobileSubscriber(String _name) {
        name = _name;
    }

    public void update(String videoTitle) {
        System.out.println("[Mobile "+name+"] Channel has uploaded video "+videoTitle);
    }
}

interface Channel {
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscriber(String title);
}

class YoutubeChannel implements Channel {
    String name;
    private List<Subscriber> subscribers;

    YoutubeChannel(String _name) {
        name = _name;
        subscribers = new ArrayList<>();
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscriber(String videoTitle) {
        for(Subscriber subscriber : subscribers) {
            subscriber.update(videoTitle);
        }
    }

    public void uploadVideo(String title) {
        System.out.println(title+" Video is uploaded");
        notifySubscriber(title);
    }
}

public class Main {
    public static void main(String args[]) {
        YoutubeChannel youtubeChannel = new YoutubeChannel("Rahul Chaudhari");
        youtubeChannel.subscribe(new MobileSubscriber("Rahul's Iphone"));
        youtubeChannel.subscribe(new EmailSubscriber("rahul@gmail.com"));

        youtubeChannel.uploadVideo("Preparation strategy for Amazon");
    }
}
