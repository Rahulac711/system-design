package com.java.structuralDesignPattern.decoratorPattern;


/*
Introduction
Structural design patterns are concerned with the composition of classes and objects. They focus on how to
assemble classes and objects into larger structures while keeping these structures flexible and efficient.
Decorator Pattern is one of the most important structural design patterns. Let's understand in depth.

Decorator Pattern
The Decorator Pattern is a structural design pattern that allows behavior to be added to individual objects,
dynamically at runtime, without affecting the behavior of other objects from the same class.

It wraps an object inside another object that adds new behaviors or responsibilities at runtime,
keeping the original object's interface intact.

The Decorator Pattern solves the above discussed Pizza problem. It allows us to add responsibilities
(like toppings) to objects dynamically without modifying their structure.
 */
interface Pizza {
    String description();

    double cost();
}

class BasicPizza implements Pizza {

    @Override
    public String description() {
        return "Basic pizza";
    }

    @Override
    public double cost() {
        return 10;
    }
}

class Margarita implements Pizza {

    @Override
    public String description() {
        return "Margarita pizza";
    }

    @Override
    public double cost() {
        return 20;
    }
}

/*
 -- Decorator class --
 */
abstract class PizzaDecorator implements Pizza {
    Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

class Olive extends PizzaDecorator {

    public Olive(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String description() {
        return pizza.description() + " with olive";
    }

    @Override
    public double cost() {
        return pizza.cost() + 10;
    }

}

class Cheese extends PizzaDecorator {

    public Cheese(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String description() {
        return pizza.description() + " with cheese";
    }

    @Override
    public double cost() {
        return pizza.cost() + 15;
    }
}

class DecoratorPattern {

    public static void main(String []args) {
        Pizza pizza = new Margarita();

        pizza = new Olive(pizza);

        pizza = new Cheese(pizza);

        System.out.println(pizza.description());
        System.out.println("Cost: $" + pizza.cost());
    }
}