
package org.usfirst.frc.team3167.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /*final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;*/
   
    private static final int leftStickUpDown = 2;// positive down
    private static final int leftStickLeftRight = 1;// positive right
    private static final int rightStickUpDown = 5;// positive down
    private static final int rightStickLeftRight = 4;// positive right
    //private static final int directionalPadUpDown = ;
    private static final int directionalPadLeftRight = 6;// On/off; positive right
    //private static final int buttonA = ;// On/off
    //private static final int buttonB = ;// On/off
    //private static final int buttonX = ;// On/off
    //private static final int buttonY = ;// On/off
    //private static final int topTrigger = ;// On/off
    private static final int bottomTrigger = 3;// On/off; positive left

    private static final double turnRateScale = 0.8;
    
    private static final double pullBallInSpeed = 0.5;
    private static final double shootBallOutSpeed = 1.0;
    
    private Joystick driveStick; 
    
    private Talon leftMotorA;
    private Talon leftMotorB;
    
    private Talon rightMotorA; 
    private Talon rightMotorB;
    
    private Jaguar leftBallWheelMotor; 
    private Jaguar rightBallWheelMotor; 
    
    private RobotDrive driveA; 
    private RobotDrive driveB;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        /*chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);*/
        
        driveStick = new Joystick(1); 
        
    	leftMotorA = new Talon(0); 
        leftMotorB = new Talon(1); 
        
        rightMotorA = new Talon(2); 
        rightMotorB = new Talon(3); 
        
        leftBallWheelMotor = new Jaguar(4); 
        rightBallWheelMotor = new Jaguar(5); 
        
        driveA = new RobotDrive(leftMotorA, rightMotorA); 
        driveB = new RobotDrive(leftMotorB, rightMotorB); 
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	/*autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);*/
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	/*switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;*/
    	}

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	//Can be modularized to use 1 class for controlling shooter.
    	//Class is created, called "BallWheels" 
    	
    	if(driveStick.getRawButton(5))// Suck ball in
        {
        	leftBallWheelMotor.set(pullBallInSpeed);
            rightBallWheelMotor.set(-pullBallInSpeed);
        }
        else if(driveStick.getRawButton(3))// Shoot ball out
        {
        	leftBallWheelMotor.set(-shootBallOutSpeed);
            rightBallWheelMotor.set(shootBallOutSpeed);
        }
        else
        {
        	leftBallWheelMotor.set(0.0);
            rightBallWheelMotor.set(0.0);
        }
    	
    	driveA.arcadeDrive(-driveStick.getY(), -driveStick.getTwist() * turnRateScale);
        driveB.arcadeDrive(-driveStick.getY(), -driveStick.getTwist() * turnRateScale);
    	
    	//driveA.arcadeDrive(driveStick.getTwist(), -driveStick.getY());
        //driveB.arcadeDrive(driveStick.getTwist(), -driveStick.getY());
        
    }    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
   
}
