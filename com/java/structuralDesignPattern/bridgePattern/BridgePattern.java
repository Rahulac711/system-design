package com.java.structuralDesignPattern.bridgePattern;

interface VideoQuality {
    void load(String title);
}

class SDQuality implements VideoQuality {
    @Override
    public void load(String title) {
        System.out.println("Streaming " + title + " in SD Quality");
    }
}

class Quality4K implements VideoQuality {
    @Override
    public void load(String title) {
        System.out.println("Streaming " + title + " in 4k Quality");
    }
}

class UltraHDQuality implements VideoQuality {
    @Override
    public void load(String title) {
        System.out.println("Streaming " + title + " in UltraHDQuality");
    }
}

abstract class VideoPlayer {
    public VideoQuality quality;
    public VideoPlayer(VideoQuality videoQuality) {
        this.quality = videoQuality;
    }
    abstract void play(String title);
}

class WebPlayer extends VideoPlayer {
    public WebPlayer(VideoQuality videoQuality) {
        super(videoQuality);
    }

    @Override
    void play(String title) {
        System.out.println("Web Platform:");
        quality.load(title);
    }
}

class MobilePlayer extends VideoPlayer {
    MobilePlayer(VideoQuality quality) {
        super(quality);
    }

    void play(String title) {
        System.out.println("Mobile Player:");
        quality.load(title);
    }
}


public class BridgePattern {
    public static void main(String args[]) {
        VideoPlayer videoPlayer = new MobilePlayer(new Quality4K());
        videoPlayer.play("Inception");
    }
}