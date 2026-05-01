package com.java.behavioralDesignPattern.commandPattern.problemToSolve;

import java.util.*;

// Receiver classes - Light and AC with basic on/off methods
class Light {
    public void on() {
        System.out.println("Light turned ON");
    }

    public void off() {
        System.out.println("Light turned OFF");
    }
}

class AC {
    public void on() {
        System.out.println("AC turned ON");
    }

    public void off() {
        System.out.println("AC turned OFF");
    }
}

// Invoker - NaiveRemoteControl class to control devices
class NaiveRemoteControl {
    private Light light;
    private AC ac;
    private String lastAction = "";

    public NaiveRemoteControl(Light light, AC ac) {
        this.light = light;
        this.ac = ac;
    }

    // Command methods
    public void pressLightOn() {
        light.on();
        lastAction = "LIGHT_ON";
    }

    public void pressLightOff() {
        light.off();
        lastAction = "LIGHT_OFF";
    }

    public void pressACOn() {
        ac.on();
        lastAction = "AC_ON";
    }

    public void pressACOff() {
        ac.off();
        lastAction = "AC_OFF";
    }

    // Undo last action
    public void pressUndo() {
        switch (lastAction) {
            case "LIGHT_ON": light.off(); lastAction = "LIGHT_OFF"; break;
            case "LIGHT_OFF": light.on(); lastAction = "LIGHT_ON"; break;
            case "AC_ON": ac.off(); lastAction = "AC_OFF"; break;
            case "AC_OFF": ac.on(); lastAction = "AC_ON"; break;
            default: System.out.println("No action to undo."); break;
        }
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        AC ac = new AC();
        NaiveRemoteControl remote = new NaiveRemoteControl(light, ac);

        remote.pressLightOn();
        remote.pressACOn();
        remote.pressLightOff();
        remote.pressUndo(); // Should undo LIGHT_OFF -> Light ON
        remote.pressUndo(); // Should undo AC_ON -> AC OFF
    }
}
/*
Issues in the Code
1. Tight Coupling:
The NaiveRemoteControl class directly calls methods on the Light and AC classes. If additional devices need to be
added in the future, changes will be required in the remote control class. This violates the open/closed principle,
where classes should be open for extension but closed for modification.
2. Lack of Flexibility:
The commands are hardcoded in the remote control class. If new actions or different command sequences are required,
modifying the remote control code is necessary, leading to potential maintenance challenges.
3. Undo Functionality:
The pressUndo method is tightly coupled with the commands. This makes it difficult to add more complex undo
functionality, especially when dealing with multiple actions or a variety of devices.
4. Hardcoded Commands:
The remote control class directly defines commands like pressLightOn, pressACOn, etc. This makes the system
rigid and difficult to modify. Adding new actions or commands would require changing the remote control code,
leading to challenges in maintaining or extending the system.
5. Maintaining Command History:
The original approach doesn’t have a centralized mechanism to track previously executed commands. This leads to
difficulties in implementing features like undo, where the last action needs to be reversed efficiently.
*/
