package org.usfirst.frc.team3167.autonomous;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
	
	private Talon leftMotorA;
	private Talon leftMotorB;
	
	private Talon rightMotorA; 
	private Talon rightMotorB;
	
	private static final double halfSpeed = 0.5; 
	private static final double fullSpeed = 1.0; 
	
	Timer timer = new Timer(); 
	
	public Autonomous(int lChannelA, int lChannelB,
			int rChannelA, int rChannelB) {

		leftMotorA = new Talon(lChannelA); //0
		leftMotorB = new Talon(lChannelB); //1
		
		rightMotorA = new Talon(rChannelA); //2
		rightMotorB = new Talon(rChannelB); //3	
	}
	
	public void run() {
		
		timer.start();
		
		//TODO: Find which motors to spin in reverse and which ones to spin regularly
		//TODO: Check if all motors spin at same rate, if not, adjust in code
		leftMotorA.set(fullSpeed);
		leftMotorB.set(fullSpeed);
		
		rightMotorA.set(-fullSpeed);
		rightMotorB.set(-fullSpeed);
		
		//TODO: Find how long we want the robot to drive forward
		timer.delay(7);	
	}
}

