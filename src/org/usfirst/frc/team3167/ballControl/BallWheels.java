package org.usfirst.frc.team3167.ballControl;

import edu.wpi.first.wpilibj.Jaguar;

public class BallWheels {
	
	private static final double pullBallInSpeed = 0.5;
    private static final double shootBallOutSpeed = 1.0;
	
	private final Jaguar leftBallWheelMotor; 
    private final Jaguar rightBallWheelMotor;

    public BallWheels(int leftMotorChannel, int rightMotorChannel) {
    	leftBallWheelMotor = new Jaguar(leftMotorChannel);
    	rightBallWheelMotor = new Jaguar(rightMotorChannel);
    }
    
    public void pullBallIn() {
    	leftBallWheelMotor.set(pullBallInSpeed);
        rightBallWheelMotor.set(-pullBallInSpeed);
    }
    
    public void shootBallOut() {
    	leftBallWheelMotor.set(-shootBallOutSpeed);
        rightBallWheelMotor.set(shootBallOutSpeed);
    }
    
    public void stop() {
    	leftBallWheelMotor.set(0.0);
        rightBallWheelMotor.set(0.0);
    }
}
