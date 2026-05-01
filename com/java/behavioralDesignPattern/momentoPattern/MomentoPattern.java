package com.java.behavioralDesignPattern.momentoPattern;

/*
Formal Definition
The Memento Pattern is a behavioral design pattern that allows an object to capture its internal state and restore
it later without violating encapsulation. It is especially useful when implementing features like undo/redo or rollback.
Key Components
This pattern defines three key components:
Originator: The object whose internal state we want to save and restore.
Memento: A storage object that holds the snapshot of the originator’s state.
Caretaker: The object responsible for requesting the memento and keeping track of it. It neither modifies nor examines
the contents of the memento.

The Solution
The issues in the previous implementation can be effectively solved using the Memento Pattern.
This pattern enables the originator (the object whose state we want to save) to produce a memento (a snapshot of
its internal state), which can then be managed by a caretaker. The key advantage is that the object’s internal state
is restored without breaking encapsulation, and we can maintain a history of changes.

The Memento Pattern introduces three components:

Originator: The object whose state we want to capture and restore. (In this case: ResumeEditor)
Memento: An immutable object that stores the internal state of the originator.
Caretaker: The object that holds and manages multiple mementos, enabling undo operations. (In this case: ResumeHistory)

Here’s the updated code implementing the Memento Pattern:
*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

// Originator with Memento inside
class ResumeEditor {
    private String name;
    private String education;
    private String experience;
    private List<String> skills = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    public void addSkills(String skill) {
        skills.add(skill);
    }

    public void printResume() {
        System.out.println("x:----- Resume -----");
        System.out.println("Name: " + name);
        System.out.println("Education: " + education);
        System.out.println("Experience: " + experience);
        System.out.println("Skills: " + skills);
        System.out.println("x:------------------");
    }

    public Momento save() {
        return new Momento(name, education, experience, List.copyOf(skills));
    }

    public void restore(ResumeEditor.Momento momento) {
        this.name = momento.getName();
        this.experience = momento.getExperience();
        this.education = momento.getEducation();
        this.skills = momento.getSkills();
    }

    public static class Momento {
        private final String name;
        private final String education;
        private final String experience;
        private List<String> skills = new ArrayList<>();

        Momento(String name, String education, String experience, List<String> skills) {
            this.name = name;
            this.education = education;
            this.experience = experience;
            this.skills = skills;
        }

        private String getName() {
            return this.name;
        }
        private String getEducation() {
            return this.education;
        }
        private String getExperience() {
            return this.experience;
        }
        private List<String> getSkills() {
            return this.skills;
        }
    }
}

class ResumeHistory {
    Stack<ResumeEditor.Momento> stack = new Stack<>();

    public void save(ResumeEditor resumeEditor) {
        stack.add(resumeEditor.save());
    }

    public void undo(ResumeEditor editor) {
        if(!stack.isEmpty()) {
            editor.restore(stack.pop());
        }
    }
}

public class MomentoPattern {
    public static void main(String args[]) {
        ResumeEditor editor = new ResumeEditor();
        ResumeHistory history = new ResumeHistory();

        editor.setName("Bob");
        editor.setEducation("B. Music");
        editor.setExperience("OG Singer with 20+ years of experience");
        editor.setSkills(Arrays.asList("Singing", "Song writing"));
        history.save(editor);

        editor.setName("Bob Marley");
        editor.setExperience("SDE Intern at TUF+");
        editor.setSkills(Arrays.asList("Java", "DSA", "LLD", "Spring Boot"));
        history.save(editor);

        history.undo(editor);
        editor.printResume();

        history.undo(editor);
        System.out.println();


        editor.printResume();
    }
}
