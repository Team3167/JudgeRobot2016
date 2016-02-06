package org.usfirst.frc3167.JudgeRobot2016;

import edu.wpi.first.wpilibj.Talon;

public class BallWheels {

	private Talon leftBallWheelMotor;
    private Talon rightBallWheelMotor;
    
    private static final double pullBallInSpeed = 0.5;
    private static final double shootBallOutSpeed = 1.0;
    
    public BallWheels(int leftChannel, int rightChannel) {
    	leftBallWheelMotor = new Talon(1);  //public Jaguar(int slot, int channel)
        rightBallWheelMotor = new Talon(2);
    }
    
    public void pullIn() {
    	leftBallWheelMotor.set(pullBallInSpeed);
        rightBallWheelMotor.set(-pullBallInSpeed);
    }
    
    public void shootOut() {
    	leftBallWheelMotor.set(-shootBallOutSpeed);
        rightBallWheelMotor.set(shootBallOutSpeed);
    }
    
    public void stop() {
    	leftBallWheelMotor.set(0.0);
        rightBallWheelMotor.set(0.0);
    }
}
