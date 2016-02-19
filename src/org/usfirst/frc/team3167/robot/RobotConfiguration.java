package org.usfirst.frc.team3167.robot;

import edu.wpi.first.wpilibj.Talon;

/*
 * Configuartion for xbox controller axis and button input values.
 * None of these are relevant for the current Joystick we are
 * using. They're just left here for any last minute changes
 * we might make regarding control of the robot. 
 */
public class RobotConfiguration {
    
	public static final int leftStickUpDown = 2;// positive down
    public static final int leftStickLeftRight = 1;// positive right
    public static final int rightStickUpDown = 5;// positive down
    public static final int rightStickLeftRight = 4;// positive right
    //public static final int directionalPadUpDown = ;
    public static final int directionalPadLeftRight = 6;// On/off; positive right
    //public static final int buttonA = ;// On/off
    //public static final int buttonB = ;// On/off
    //public static final int buttonX = ;// On/off
    //public static final int buttonY = ;// On/off
    //public static final int topTrigger = ;// On/off
    public static final int bottomTrigger = 3;// On/off; positive left
    
    public static final double frequency = 50.0;// [Hz]
    public static final double timeStep = 1.0 / frequency;// [sec]
}
