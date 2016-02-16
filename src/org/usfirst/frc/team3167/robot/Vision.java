package org.usfirst.frc.team3167.robot;

import edu.wpi.first.wpilibj.CameraServer;

public class Vision {
	
	public void enable() 
	{
		CameraServer camServer = CameraServer.getInstance();  
		
		camServer.setQuality(25); 
		camServer.setSize(75); 
		
		camServer.startAutomaticCapture("cam0");
	}
}
