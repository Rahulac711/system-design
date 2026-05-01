package com.java.behavioralDesignPattern.momentoPattern.problemToSolve;

import java.util.*;

/*
Real-Life Analogy: Undo/Redo in Text Editors
Think of the Memento Pattern as an undo/redo mechanism. When you type or edit something in a text editor, the
application captures snapshots of the document at different points. Each snapshot (memento) is stored by an
external caretaker (like a history stack), and the editor (originator) can revert to these snapshots when needed,
without exposing its internal logic.

A key strength of the pattern is that the originator alone is responsible for creating its snapshots, thus preserving
encapsulation while still allowing state recovery.

Let's now understand the working of the Memento Pattern through the help of a problem statement.
Understanding the Problem
Assume we are building a resume editor where a user can make changes to their resume - such as name, education,
experience, or skills, and may also want the ability to undo or redo changes. To do this, we need a way to take a
snapshot of the resume at any point in time and restore it later.

Below is a basic implementation trying to mimic this functionality:
 */

// Originator class: stores the current state of the resume
class ResumeEditor {
    String name;
    String education;
    String experience;
    List<String> skills;
}

// ResumeSnapshot acts like a memento, but isn't encapsulated properly
class ResumeSnapshot {
    public String name;
    public String education;
    public String experience;
    public List<String> skills;

    // Constructor: captures the current state from ResumeEditor
    public ResumeSnapshot(ResumeEditor editor) {
        this.name = editor.name;
        this.education = editor.education;
        this.experience = editor.experience;
        this.skills = new ArrayList<>(editor.skills); // Deep copy
    }

    // Restore function: applies the stored state back to ResumeEditor
    public void restore(ResumeEditor editor) {
        editor.name = this.name;
        editor.education = this.education;
        editor.experience = this.experience;
        editor.skills = new ArrayList<>(this.skills); // Deep copy
    }
}

// Main driver to demonstrate snapshot creation and restoration
class Main {
    public static void main(String[] args) {
        ResumeEditor editor = new ResumeEditor();
        editor.name = "Alice";
        editor.education = "B.Tech in CS";
        editor.experience = "2 years at ABC Corp";
        editor.skills = new ArrayList<>(Arrays.asList("Java", "SQL"));

        // Step 1: Create a snapshot before making changes
        ResumeSnapshot snapshot = new ResumeSnapshot(editor);

        // Step 2: Modify the resume
        editor.name = "Alice Johnson";
        editor.skills.add("Spring Boot");

        System.out.println("After changes:");
        System.out.println("Name: " + editor.name);
        System.out.println("Skills: " + editor.skills);

        // Step 3: Restore previous state using snapshot
        snapshot.restore(editor);

        System.out.println("\nAfter undo:");
        System.out.println("Name: " + editor.name);
        System.out.println("Skills: " + editor.skills);
    }
}
/*
Issues in the Above Code
No Caretaker Role
The snapshot is being manually handled inside the main() method. There's no dedicated class to manage multiple states.
No Undo/Redo Stack
Only a single snapshot is supported. You can't perform multiple levels of undo or redo.
Breaks Encapsulation
The fields in ResumeSnapshot are public. This exposes internal details and violates encapsulation.
Tightly Coupled Implementation
ResumeSnapshot directly accesses and depends on the internal structure of ResumeEditor. If the fields change, the
snapshot class must change too.
No Abstraction
There's no abstraction to hide how snapshots are created or restored. Everything is directly visible and modifiable.
*/
