package com.java.behavioralDesignPattern.mediatorPattern;

import java.util.ArrayList;
import java.util.List;

/*
The current implementation of the collaborative document editor can be improved by refactoring the code using the
Mediator Pattern. Instead of having users directly communicate with each other to notify changes, the
CollaborativeDocument will act as the mediator. This way, users only interact with the document (mediator) to
communicate changes, promoting loose coupling and simplifying the overall structure.

Explanation of Changes
Mediator Interface (DocumentSessionMediator):
Defines the methods for broadcasting changes and adding users to the document.
Concrete Mediator (CollaborativeDocument):
Implements the DocumentSessionMediator and manages users. It broadcasts changes to all users except the sender.
User Class:
Now interacts only with the CollaborativeDocument (mediator) rather than directly notifying other users. This
reduces the coupling between users.

Issue	How it is Solved
*Tight Coupling Between Users:	The users no longer hold references to each other. They communicate through the
mediator (CollaborativeDocument), reducing direct dependencies between them.

*Adding/Removing Users Breaks the Structure:	The CollaborativeDocument now manages the users and their interactions.
Adding or removing users is handled centrally, making the system more maintainable.

*Hard to Orchestrate Roles (Editor/Viewer/Admin):	Roles can now be managed through the mediator. Different roles and
permissions can be introduced in the CollaborativeDocument class, making the structure more flexible.
Difficulty in Managing Permissions, States, and Notifications:	The mediator centralizes the notifications and can be
extended to manage permissions, states, and notifications more efficiently.

*Lack of Separation of Concerns:	The User class now only handles user-specific behavior (making changes and receiving
changes), while the mediator handles all communication, adhering to the Single Responsibility Principle.
Scalability Issues:	With the mediator handling communication, it is easier to scale the system, as new users or
features (like different types of notifications) can be added without altering the existing structure.
*/
interface DocumentMediator {
    void join(User user);
    void broadcast(String change, User send);
}

class CollaborativeDocument implements DocumentMediator {
    List<User> userList;

    CollaborativeDocument() {
        userList = new ArrayList<>();
    }

    public void join(User user) {
        userList.add(user);
    }

    public void broadcast(String chagne, User sender) {
        for(User user : userList) {
            if(user != sender) {
                user.receiveChanges(chagne, sender);
            }
        }
    }
}

class User {
    private String name;
    private CollaborativeDocument collaborativeDocument;

    User(String _name, CollaborativeDocument document) {
        name = _name;
        collaborativeDocument = document;
    }

    public void receiveChanges(String change, User sender) {
        System.out.println(name + " saw change from " + sender.name + ": \"" + change + "\"");
    }

    public void makeChange(String change) {
        System.out.println(name + " edited the document: " + change);
        collaborativeDocument.broadcast(change, this);
    }
}

public class MediatorPattern {
    public static void main(String args[]) {
        CollaborativeDocument collaborativeDocument = new CollaborativeDocument();

        User user = new User("John", collaborativeDocument);
        User user1 = new User("Bob", collaborativeDocument);
        User user2 = new User("Charles", collaborativeDocument);

        collaborativeDocument.join(user);
        collaborativeDocument.join(user1);
        collaborativeDocument.join(user2);

        user.makeChange("Add title");
        user1.makeChange("added summary");
    }
}
