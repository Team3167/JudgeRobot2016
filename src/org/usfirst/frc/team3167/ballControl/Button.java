package org.usfirst.frc.team3167.ballControl;

import edu.wpi.first.wpilibj.Joystick;

public class Button {

	private final Joystick stick;
	private final int button;
	
	public Button(Joystick stick, int button) {
		this.stick = stick;
		this.button = button;
	}
	
	private boolean pressedLastCheck = false;
	public boolean justPressed() {
		if (stick.getRawButton(button)) {
			if (pressedLastCheck) {
				return false;
			} else {
				pressedLastCheck = true;
				return true;
			}
		}
		
		pressedLastCheck = false;
		return false;
	}
}
