package org.usfirst.frc.team3167.autonomous;

import org.usfirst.frc.team3167.robot.RobotConfiguration;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
			
	private static final double halfSpeed = 0.5; 
	private static final double fullSpeed = 1.0;  
	
	Timer timer = new Timer(); 

	public void run() {
		
		timer.start();
		
		//TODO: Find which motors to spin in reverse and which ones to spin regularly
		//TODO: Check if all motors spin at same rate, if not, adjust in code
		RobotConfiguration.leftMotorA.set(fullSpeed);
		RobotConfiguration.leftMotorB.set(fullSpeed);
		
		RobotConfiguration.rightMotorA.set(-fullSpeed);
		RobotConfiguration.rightMotorB.set(-fullSpeed);
		
		//TODO: Find how long we want the robot to drive forward
		timer.delay(7);	
	}
}

