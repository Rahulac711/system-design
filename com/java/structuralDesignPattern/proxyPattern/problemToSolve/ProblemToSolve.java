package com.java.structuralDesignPattern.proxyPattern.problemToSolve;

class RealVideoDownloader {

    void downloadVideo(String link) {
        System.out.println("Video downloading started from: "+link);
    }
}

public class ProblemToSolve {
    public static void main(String agrs[]) {
        RealVideoDownloader realVideoDownloader = new RealVideoDownloader();
        realVideoDownloader.downloadVideo("https://video.com/inception");

        RealVideoDownloader realVideoDownloader2 = new RealVideoDownloader();
        realVideoDownloader2.downloadVideo("https://video.com/inception");
    }
}
