package com.java.structuralDesignPattern.flyweightPattern;

/*

Flyweight Pattern
The Flyweight Pattern is a structural design pattern used to minimize memory usage by sharing as much data as possible
with similar objects.

It separates the intrinsic (shared) state from the extrinsic (unique) state, so that shared parts of objects are stored
only once and reused wherever needed.
Real-Life Analogy
Think of trees in a video game. In an open-world video game, you might see thousands of trees:
All oak trees have the same texture, shape, and behavior (shared/intrinsic).
But their location, size, or health status may differ (extrinsic).

Rather than loading the same tree model thousands of times, the game engine uses a single shared tree model and passes
different parameters when rendering.
Problem It Solves
It solves the problem of high memory usage when a large number of similar objects are created. For example, imagine a
 system rendering:
Thousands of tree objects in a forest
Each with the same shape and texture but a different location

Instead of creating thousands of identical objects, the Flyweight Pattern lets you share the common parts (shape,
 texture) and store the unique parts (location) externally, dramatically reducing memory consumption.
Core Concepts
Intrinsic State: The immutable, shared data stored inside the flyweight. It is independent of context.
Extrinsic State: The context-specific data passed from the client and not stored in the flyweight.

*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class TreeType {
    String name;
    String colour;
    String texture;

    public TreeType(String name, String colour, String texture) {
        this.name = name;
        this.colour = colour;
        this.texture = texture;
    }

    public void draw(int x, int y) {
        System.out.println("Drawing "+name+"tree at"+"(x:" + x+", y: "+y+")");
    }
}

class Tree {
    int x;
    int y;
    TreeType treeType;

    Tree(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }

    void draw() {
        treeType.draw(x, y);
    }
}

class TreeFactory {
    HashMap<String, TreeType> map = new HashMap<>();

    public TreeType getTreeType(String name, String colour, String texture) {
        String key = name +"-" + colour +"-"+ texture;
        if(!map.containsKey(key)) {
            map.put(key, new TreeType(name, colour, texture));
        }
        return map.get(key);
    }
}

class Forest {
    TreeFactory treeFactory;
    Forest() {
        treeFactory = new TreeFactory();
    }
    List<Tree> treeList = new ArrayList<>();

    public void addTree(int x, int y, String name, String color, String texture) {
        treeList.add(new Tree(x, y, treeFactory.getTreeType(name, color, texture)));
    }

    void draw() {
        for(Tree tree : treeList) {
            tree.draw();
        }
    }
}

public class FlyweightPattern {
    public static void main(String args[]) {
        Forest forest = new Forest();
        for(int i=0; i<100000; i++) {
            forest.addTree(10, 10, "Oak", "Green", "Rough");
        }
        System.out.println("1 million tree planted");
    }
}
