package org.usfirst.frc.team3167.drive;

import org.usfirst.frc.team3167.robot.InputWarper;
import org.usfirst.frc.team3167.robot.Robot;
import org.usfirst.frc.team3167.robot.RobotConfiguration;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class QuadArcadeDrive {
	
	private final Talon leftMotorA;
	private final Talon leftMotorB;
	private final Talon rightMotorA;
	private final Talon rightMotorB;

	
	private final RobotDrive driveA; 
    private final RobotDrive driveB;
    
    private final AnalogGyro gyro;
    
    private double moveRateScale = 1.0;
    private double turnRateScale = 0.8;
    
    private final InputWarper moveWarper;
    private final InputWarper turnWarper;
    
    private final double turnLoopKp = 0.4;// [1/%]
    private final double turnLoopTi = 3.2;// [sec]
    private double turnErrorIntegral = 0.0;
    private final double maxTurnErrorIntegral = 0.75;
    private final double maxRotationRate = 1800.0;// [deg/sec]
    
    public QuadArcadeDrive(int leftChannelA, int leftChannelB,
			int rightChannelA, int rightChannelB) {
 
    	leftMotorA = new Talon(leftChannelA);
		leftMotorB = new Talon(leftChannelB);
		rightMotorA = new Talon(rightChannelA);
		rightMotorB = new Talon(rightChannelB);
		
		driveA = new RobotDrive(leftMotorA, rightMotorA); 
        driveB = new RobotDrive(leftMotorB, rightMotorB);
        
        moveWarper = new InputWarper();
        turnWarper = new InputWarper();
        
        gyro = null;
   	}
	
	public QuadArcadeDrive(int leftChannelA, int leftChannelB,
			int rightChannelA, int rightChannelB, int gyroChannel) {

		leftMotorA = new Talon(leftChannelA);
		leftMotorB = new Talon(leftChannelB);
		rightMotorA = new Talon(rightChannelA);
		rightMotorB = new Talon(rightChannelB);
		
		driveA = new RobotDrive(leftMotorA, rightMotorA); 
        driveB = new RobotDrive(leftMotorB, rightMotorB);
        
        moveWarper = new InputWarper();
        turnWarper = new InputWarper();
        
        gyro = new AnalogGyro(gyroChannel);
        //Find ideal sensitivity
        gyro.setSensitivity(0.0007);// 7 mV/deg/sec
	}
	
	public void setTurnRateScale(double newScale) {
		turnRateScale = newScale;
	}
	
	public void drive(double moveValue, double rotateValue) {
		moveValue = moveWarper.warp(moveValue) * moveRateScale;
		rotateValue = moveWarper.warp(rotateValue) * turnRateScale;
		
		// If we have a gyro object, use it to "close the loop" around
		// the rotation rate
		// Because rotateValue is a -1 to +1 value, we scale the value
		// returned from gyro.getRate() to ensure consistent units.
		if (gyro != null) {
			double error = rotateValue - gyro.getRate() / maxRotationRate;
			
			if (turnLoopTi > 0.0) {
				turnErrorIntegral += error * RobotConfiguration.timeStep;
				if (turnErrorIntegral > maxTurnErrorIntegral) {
					turnErrorIntegral = maxTurnErrorIntegral;
				} else if (turnErrorIntegral < -maxTurnErrorIntegral) {
					turnErrorIntegral = -maxTurnErrorIntegral;
				}
				
				rotateValue = turnLoopKp * (error
						+ turnErrorIntegral / turnLoopTi);				
				
			} else {
				rotateValue = turnLoopKp * error;
			}
		}
		
		driveA.arcadeDrive(moveValue, rotateValue);
		driveB.arcadeDrive(moveValue, rotateValue);
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
