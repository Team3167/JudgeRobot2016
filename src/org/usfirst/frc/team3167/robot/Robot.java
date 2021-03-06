
package org.usfirst.frc.team3167.robot;

import org.usfirst.frc.team3167.autonomous.Autonomous;
import org.usfirst.frc.team3167.ballControl.BallWheels;
import org.usfirst.frc.team3167.ballControl.Button;
import org.usfirst.frc.team3167.drive.QuadArcadeDrive;
import org.usfirst.frc.team3167.vision.Vision;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

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
    
    private Joystick driveStick; 
    private Joystick driveStick2; 
    
    private BallWheels ballWheels;
    private QuadArcadeDrive drive;
    private Vision vision; 
    private Autonomous autonomous;
    
    // Temporary stuff for adjusting values
    private Button adjMoveWarpUp;
    private Button adjMoveWarpDown;
    private Button adjTurnWarpUp;
    private Button adjTurnWarpDown;
    private Button adjTurnScaleUp;
    private Button adjTurnScaleDown; 
	
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
        driveStick2 = new Joystick(2); 
        
        drive = new QuadArcadeDrive(0, 1, 2, 3, 0);
        ballWheels = new BallWheels(4, 5);
        vision = new Vision();       
        
        drive.setWarping(1.5, 1.5);
        
        adjMoveWarpUp = new Button(driveStick, 7);
        adjMoveWarpDown = new Button(driveStick, 8);
        adjTurnWarpUp = new Button(driveStick, 9);
        adjTurnWarpDown = new Button(driveStick, 10);
        adjTurnScaleUp = new Button(driveStick, 11);
        adjTurnScaleDown = new Button(driveStick, 12);
        
        //Start capturing and displaying video to FRC PC Dashboard or the SmarDashboard.
        //Open "Java" smart dashboard from driver station to use.
        vision.enable();
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
    // Distance to cover during autonomous is about 74 inches
    // +/- depending on placement of robot
    private static final double autoDriveTime = 3.0;// [sec]
    private static final double autoDriveSpeed = 0.75;// Not sure this is the best value
    public void autonomousInit() {
    	/*autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);*/
    	autonomous = new Autonomous(drive, autoDriveSpeed, autoDriveTime); 
    }
    
    private double clamp(double in, double min, double max) {
    	if (in < min) {
    		return min;
    	} else if (in > max) {
    		return max;
    	}
    	return in;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	//Call method "run" from class Autonomous, during autonomous
    	autonomous.run();
    }
    
    private void adjustDriveConfiguration() {
    	if (adjMoveWarpUp.justPressed()) {
    		drive.setWarping(clamp(drive.getMoveWarp() * 1.2, 1.0, 3.0),
    				drive.getTurnWarp());
    	} else if (adjMoveWarpDown.justPressed()) {
    		drive.setWarping(clamp(drive.getMoveWarp() * 0.8, 1.0, 3.0),
    				drive.getTurnWarp());
    	}
    	
    	if (adjTurnWarpUp.justPressed()) {
    		drive.setWarping(drive.getMoveWarp(),
    				clamp(drive.getTurnWarp() * 1.2, 1.0, 3.0));
    	} else if (adjTurnWarpDown.justPressed()) {
    		drive.setWarping(drive.getMoveWarp(),
    				clamp(drive.getTurnWarp() * 0.8, 1.0, 3.0));
    	}
    	
    	if (adjTurnScaleUp.justPressed()) {
    		drive.setTurnRateScale(clamp(drive.getTurnScale() * 1.2, 0.1, 1.0));
    	} else if (adjTurnScaleDown.justPressed()) {
    		drive.setTurnRateScale(clamp(drive.getTurnScale() * 0.8, 0.1, 1.0));
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {

    	if(driveStick.getRawButton(2) || driveStick2.getRawButton(2))// Suck ball in
        {
        	ballWheels.pullBallIn();
        }
        else if(driveStick.getRawButton(1) || driveStick2.getRawButton(1))// Shoot ball out
        {
        	ballWheels.shootBallOut();
        }
        else
        {
        	ballWheels.stop();
        }
    	
    	drive.drive(-driveStick.getY(), -driveStick.getTwist());
    	
    	adjustDriveConfiguration();
    	
    }    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
   
}
