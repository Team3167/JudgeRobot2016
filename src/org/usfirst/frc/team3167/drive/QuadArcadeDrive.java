package org.usfirst.frc.team3167.drive;

import org.usfirst.frc.team3167.robot.InputWarper;
import org.usfirst.frc.team3167.robot.Robot;
import org.usfirst.frc.team3167.robot.RobotConfiguration;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class QuadArcadeDrive {
	
	private RobotDrive driveA; 
    private RobotDrive driveB;
    
    private double moveRateScale = 1.0;
    private double turnRateScale = 0.8;
    
    private InputWarper moveWarper;
    private InputWarper turnWarper;
    
	public QuadArcadeDrive() {

		driveA = new RobotDrive(RobotConfiguration.leftMotorA, RobotConfiguration.rightMotorA); 
        driveB = new RobotDrive(RobotConfiguration.leftMotorB, RobotConfiguration.rightMotorB);
        
        moveWarper = new InputWarper();
        turnWarper = new InputWarper();
	}
	
	public void setTurnRateScale(double newScale) {
		turnRateScale = newScale;
	}
	
	public void drive(double moveValue, double rotateValue) {
		moveValue = moveWarper.warp(moveValue);
		rotateValue = moveWarper.warp(rotateValue);
		
		driveA.arcadeDrive(moveValue * moveRateScale, rotateValue * turnRateScale);
		driveB.arcadeDrive(moveValue * moveRateScale, rotateValue * turnRateScale);
	}
	
	public void setWarping(double moveWarp, double turnWarp) {
		moveWarper.setWarp(moveWarp);
		turnWarper.setWarp(turnWarp);
	}
	
	public double getMoveWarp() {
		return moveWarper.getWarp();
	}
	
	public double getTurnWarp() {
		return turnWarper.getWarp();
	}
	
	public double getMoveScale() {
		return moveRateScale;
	}
	
	public double getTurnScale() {
		return turnRateScale;
	}
}
