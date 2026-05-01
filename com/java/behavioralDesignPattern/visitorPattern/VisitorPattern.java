package com.java.behavioralDesignPattern.visitorPattern;

import java.util.ArrayList;
import java.util.List;

interface Item {
    void accept(Itemvisitor visitor);
}

class PhysicalItem implements Item {
    String name;
    int weight;

    PhysicalItem(String _name, int _weight) {
        name = _name;
        weight = _weight;
    }

    public void accept(Itemvisitor visitor) {
        visitor.visit(this);
    }
}

class DigitalItem implements Item {
    String name;
    int sizeInMb;

    public DigitalItem(String _name, int _size) {
        name = _name;
        sizeInMb = _size;
    }

    public void accept(Itemvisitor visitor) {
        visitor.visit(this);
    }
}

class GiftCard implements Item {

    String code;
    int amount;

    GiftCard(String _name, int _amount) {
        code = _name;
        amount = _amount;
    }

    public void accept(Itemvisitor visitor) {
        visitor.visit(this);
    }
}


interface Itemvisitor {
    void visit(PhysicalItem item);
    void visit(DigitalItem item);
    void visit(GiftCard item);
}

class Invoicevisitor implements Itemvisitor {
    public void visit(PhysicalItem item) {
        System.out.println("Invoice: " + item.name + " - Shipping to customer");
    }

    public void visit(DigitalItem item) {
        System.out.println("Invoice: " + item.name + " - Email with download link");
    }

    public void visit(GiftCard item) {
        System.out.println("Invoice: Gift Card - Code: " + item.code);
    }
}

class ShippingVisitor implements Itemvisitor {
    public void visit(PhysicalItem item) {
        System.out.println("Shipping cost for " + item.name + ": Rs. " + (item.weight * 10));

    }
    public void visit (DigitalItem item) {
        System.out.println(item.name + " is digital -- No shipping cost.");
    }
    public void visit(GiftCard item) {
        System.out.println("GiftCard delivery via email -- No shipping cost.");
    }
}


public class VisitorPattern {
    public static void main(String args[]) {
        List<Item> items = new ArrayList<>();
        items.add(new PhysicalItem("Watch", 100));
        items.add(new DigitalItem("Ebook", 100));
        items.add(new GiftCard("Amazon_card", 100));

        Itemvisitor invoicevisitor = new Invoicevisitor();
        Itemvisitor shippingVisitor = new ShippingVisitor();

        for(Item item : items) {
            item.accept(invoicevisitor);
            item.accept(shippingVisitor);
        }
    }
}
