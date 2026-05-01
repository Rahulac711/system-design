package StrategyAndObserver;

import java.util.*;

public class StockPriceNotifier {

    Set<Observer> subscriberlist = new HashSet<Observer>();
    Map<String, Float> stockPrice = new HashMap<String, Float>();
    float price;
    String stockname;

    public void addObserver(Observer observer) {
        subscriberlist.add(observer);
    }

    public void removeObserver(Observer observer) {
        subscriberlist.remove(observer);
    }

    public void setstockprice(float price, String stockname) {
        this.price = price;
        this.stockname = stockname;
        stockPrice.put(stockname, price);
        notifyObserver(price, stockname);
    }

    public void notifyObserver(float price, String stockname) {
        for (Observer observer : subscriberlist)
            observer.update(price, stockname);
    }

}

interface Observer {
    public void update(float price, String stockname);
}

class Subscriber1 implements Observer {
    @Override
    public void update(float price, String stockname) {
        System.out.println("Subscriber1: " + stockname + " has price: " + price);
    }
}

class Main {
    public static void main(String[] args) {
        StockPriceNotifier stockPriceNotifier = new StockPriceNotifier();

        Subscriber1 subscriber1 = new Subscriber1();
        stockPriceNotifier.addObserver(subscriber1);

        stockPriceNotifier.setstockprice(100, "Reliance");
    }
}