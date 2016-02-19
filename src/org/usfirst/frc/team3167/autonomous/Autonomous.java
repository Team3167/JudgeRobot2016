package org.usfirst.frc.team3167.autonomous;

import org.usfirst.frc.team3167.drive.QuadArcadeDrive;
import org.usfirst.frc.team3167.robot.RobotConfiguration;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
			
	private final double autoSpeed;
	private final double driveTime;// [sec]
	
	private final QuadArcadeDrive drive;
	
	// Can't use a standard java timer in a real-time application
	// We'll keep track of time on our own
	private double elapsedTime = 0.0;// [sec]
	
	public Autonomous(QuadArcadeDrive drive, double autoSpeed, double driveTime) {
		this.drive = drive;
		this.autoSpeed = autoSpeed;
		this.driveTime = driveTime;
	}

	public void run() {
		
		if (elapsedTime < driveTime) {
			drive.drive(autoSpeed, 0.0);
			elapsedTime += RobotConfiguration.timeStep;
		} else {
			drive.drive(0.0, 0.0);
		}
	}
}

