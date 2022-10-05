package org.kata.marsrover;

/*
Method receiveCommands should be used to transmit commands to the rover.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Rover {

    private Space space;

    public void receiveCommands(String commands) throws Exception {
        for (char command : commands.toCharArray()) {
            if (!receiveSingleCommand(command)) {
                break;
            }
        }
    }

    public boolean receiveSingleCommand(char command) throws Exception {
        switch(Character.toUpperCase(command)) {
            case 'F':
                return getSpace().moveForward();
            case 'B':
                return getSpace().moveBackward();
            case 'L':
                getSpace().changeDirectionLeft();
                return true;
            case 'R':
                getSpace().changeDirectionRight();
                return true;
            default:
                throw new Exception("Command " + command + " is unknown.");
        }
    }

    public String getPosition() {
        return getSpace().toString();
    }

}
