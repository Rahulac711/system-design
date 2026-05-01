package com.java.behavioralDesignPattern.commandPattern;

import java.util.Stack;

/*
Real-Life Analogy
Think of a remote control used to turn on or off the lights or an air conditioner (AC). When you press a button
to turn on the lights or adjust the temperature, you don’t need to understand how the internal circuits work or
how the AC receives the signal. You just press the "On" or "Off" button, and the remote control takes care of
sending the command.

Similarly, the Command Pattern decouples the sender of a request (the remote control) from the receiver
(the light or AC), providing flexibility and simplicity in handling commands.
Four Key Components
Client – Initiates the request and sets up the command object.
Invoker – Asks the command to execute the request.
Command – Defines a binding between a receiver object and an action.
Receiver – Knows how to perform the actions to satisfy a request.
*/

// ========= Receiver classes ===========
// Light and AC with basic on/off methods
class Light {
    void on() {
        System.out.println("Lights on");
    }

    void off() {
        System.out.println("Lights off");
    }
}

class AC {
    void on() {
        System.out.println("AC on");
    }
    void off() {
        System.out.println("AC off");
    }
}

// ========= Command interface ===========
//    defines the command structure
interface Command {
    void execute();
    void undo();
}

// Concrete commands for Light ON and OFF
class LightOnCommand implements Command {
    Light light;
    LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command {
    Light light;
    LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }
    public void undo() {
        light.on();
    }
}

// Concrete commands for AC ON and OFF
class ACOnCommand implements Command {
    AC ac;
    ACOnCommand(AC _ac) {
        ac = _ac;
    }

    public void execute() {
        ac.on();
    }
    public void undo() {
        ac.off();
    }
}

class ACOffCommand implements Command {
    AC ac;
    ACOffCommand(AC _ac) {
        ac = _ac;
    }

    public void execute() {
        ac.off();
    }
    public void undo() {
        ac.on();
    }
}

// ========== Remote control class (Invoker) ==========
class RemoteControl {
    private Command[] buttons = new Command[5];
    private Stack<Command> commandHistory = new Stack<>();

    public void setCommand(int slot, Command command) {
        buttons[slot] = command;
    }

    public void pressButton(int slot) {
        if(slot > buttons.length) {
            System.out.println("No command found");
            return;
        }
        if(buttons[slot] != null) {
            buttons[slot].execute();
            commandHistory.add(buttons[slot]);
        } else {
            System.out.println("No command found");
        }
    }

    public void pressUndo() {
        if(!commandHistory.isEmpty()) {
            commandHistory.pop().undo();
        } else {
            System.out.println("No command found");
        }
    }
}

public class CommandPattern {
    public static void main(String args[]) {
        Light light = new Light();
        AC ac = new AC();
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);
        Command acOnCommand = new ACOnCommand(ac);
        Command acOffCommand = new ACOffCommand(ac);

        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(1, lightOnCommand);
        remoteControl.setCommand(2, lightOffCommand);
        remoteControl.setCommand(3, acOnCommand);
        remoteControl.setCommand(4, acOffCommand);

        remoteControl.pressButton(1);
        remoteControl.pressButton(2);
        remoteControl.pressButton(3);
        remoteControl.pressButton(4);

        remoteControl.pressUndo();
        remoteControl.pressUndo();
        remoteControl.pressUndo();
        remoteControl.pressUndo();
    }
}
