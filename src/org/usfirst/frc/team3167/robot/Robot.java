
package org.usfirst.frc.team3167.robot;

import org.usfirst.frc.team3167.util.Conversions;
import org.usfirst.frc.team3167.util.JoystickButton;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot 
{
	private Joystick driveJoystick1 = new Joystick(1);
	private Joystick driveJoystick0 = new Joystick(0);
	  
	Preferences prefs = Preferences.getInstance();

    private static final double pullBallInSpeed = 0.5;
    private static final double shootBallOutSpeed = 1.0;

    private Jaguar leftBallWheelMotor;
    private Jaguar rightBallWheelMotor;
    private Joystick driveStick;

    private Jaguar leftMotorA;
    private Jaguar leftMotorB;

    private Jaguar rightMotorA;
    private Jaguar rightMotorB;
    private Joystick driveStick;
    private RobotDrive driveA;
    private RobotDrive driveB;

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
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {
        driveStick = new Joystick(1);
        leftBallWheelMotor = new Jaguar(1, 1);  //public Jaguar(int slot, int channel)
        rightBallWheelMotor = new Jaguar(1, 2);

        leftMotorA = new Jaguar(1, 3);
        leftMotorB = new Jaguar(1, 4);

        rightMotorA = new Jaguar(1, 5);
        rightMotorB = new Jaguar(1, 6);

        driveStick = new Joystick(1);

        driveA = new RobotDrive(leftMotorA, rightMotorA);
        driveB = new RobotDrive(leftMotorB, rightMotorB);

        driveA.arcadeDrive(-driveStick.getRawAxis(leftStickUpDown),
                -driveStick.getRawAxis(leftStickLeftRight) * turnRateScale);
        driveB.arcadeDrive(-driveStick.getRawAxis(leftStickUpDown),
                -driveStick.getRawAxis(leftStickLeftRight) * turnRateScale);

    	//prefs.putDouble("Kp", 1.0);
    	//prefs.putDouble("Ki", 0.0);
    }
    
    private void DoCommonUpdates()
    {
    	updateSmartDashboard();
    	
    	// CAN objects - this line sends the commands
    	CANJaguar.updateSyncGroup(RobotConfiguration.wheelCANSyncGroup);
    }
    
    public void updateSmartDashboard()
    {
    	//SmartDashboard.putNumber("Front Right Wheel Angle", drive.GetWheel(1).GetWheelAngle());
    	//SmartDashboard.putNumber("Rear Left Wheel Angle", drive.GetWheel(2).GetWheelAngle());
    	//SmartDashboard.putNumber("Rear Right Wheel Angle", drive.GetWheel(3).GetWheelAngle());
    	
    	//SmartDashboard.putNumber("Left Front Wheel KP", drive.GetWheel(0).getKP());
    	//SmartDashboard.putNumber("Left Front Wheel KI", drive.GetWheel(0).getKI()); 
    	//SmartDashboard.putNumber("Left Front Wheel KD", drive.GetWheel(0).getKD()); 
    	//SmartDashboard.putNumber("testSig", testSig); 
    	
    	//SmartDashboard.putNumber("Left Front Wheel speed", drive.GetWheel(0).GetWheelVelocity());
    	//SmartDashboard.putNumber("Left Front Wheel command speed", drive.GetWheel(0).getCANMotor().get() / drive.GetWheel(0).getGearRatio() * Conversions.RPMToRadPerSec);
    }
    
    public void autonomousInit()
    {
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {
    	DoCommonUpdates();
    }
    
    public void teleopInit()
    {
    }
    
    /**
     * Function called as fast as possible during operator control.
     * Handles button presses only.
     */
    public void teleopContinuous()
    {
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
    	DoCommonUpdates();
    	
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
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic()
    {
    }
    
    public void disabledInit()
    {
    	// Call test print statements to execute only one time
    	//drive.PrintTest();
    }
    
    public void disabledPeriodic()
    {
    	CANJaguar.updateSyncGroup(RobotConfiguration.wheelCANSyncGroup);
        identifyControllerButtons();
    }

    private void identifyControllerButtons()
    {
        int i;
        for (i = 0; i < 12; i++)
        {
            if (driveStick.getRawAxis(i) > 0.5)
                System.out.println(i + " -> Positive");
            else if (driveStick.getRawAxis(i) < -0.5)
                System.out.println(i + " -> Negative");
        }
    }
}
