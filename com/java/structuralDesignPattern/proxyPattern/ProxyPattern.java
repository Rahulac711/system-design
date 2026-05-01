package com.java.structuralDesignPattern.proxyPattern;

import java.util.HashMap;
import java.util.Map;

/*
Proxy Pattern
The Proxy Pattern is a structural design pattern that provides a surrogate or placeholder for another
object to control access to it.

A proxy acts as an intermediary that implements the same interface as the original object, allowing it to
intercept and manage requests to the real object.

Real-Life Analogy
Think of a personal assistant:
A busy CEO may not respond to everyone directly.
Instead, their assistant takes calls, filters emails, manages the calendar, and only involves the CEO when necessary.
The assistant controls access to the CEO while still providing essential services to others.

 */

interface VideoDownloader {
    void downloadVideo(String link);
}

class RealVideoDownloader implements VideoDownloader {

    @Override
    public void downloadVideo(String link) {
        System.out.println("Video downloading started from: "+link);
    }
}

class CacheVideoDownloader implements VideoDownloader {

    RealVideoDownloader realVideoDownloader;
    Map<String, String> cache;

    CacheVideoDownloader() {
        realVideoDownloader = new RealVideoDownloader();
        cache = new HashMap<>();
    }

    @Override
    public void downloadVideo(String link) {
        if(cache.containsKey(link)) {
            System.out.println("Video downloading started from cache: "+link);
            return;
        }

        realVideoDownloader.downloadVideo(link);
        cache.put(link, link);
    }
}

public class ProxyPattern {
    public static void main(String args[]) {
        VideoDownloader videoDownloader = new CacheVideoDownloader();
        System.out.println("User 1 tries to download the video.");
        videoDownloader.downloadVideo("https://video.com/inception");

        System.out.println("User 2 tries to download the video.");
        videoDownloader.downloadVideo("https://video.com/inception");
    }
}
