package org.usfirst.frc.team3167.vision;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class Vision {
	
	//Using CameraServer class
	CameraServer camServer = CameraServer.getInstance();  
	
	//Using USBCamera class
	//USBCamera camera = new USBCamera("cam0");
	
	//Enable camera and begin capturing and displaying video 
	public void enable() {
		setDetails(); 
		camServer.startAutomaticCapture("cam0");
		//camera.startCapture();
		
	}
	//Set the details of the camera. Size and quality can also be adjusted
	//in the SmartDashboard depending on what is needed.
	public void setDetails() {
		camServer.setQuality(25); 
		camServer.setSize(75);
	}
}

