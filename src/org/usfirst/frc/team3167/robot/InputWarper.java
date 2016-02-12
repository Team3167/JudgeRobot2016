package org.usfirst.frc.team3167.robot;

public class InputWarper {
	
	private double warp;
	
	public InputWarper() {
		setWarp(1.0);
	}
	
	public InputWarper(double warp) {
		setWarp(warp);
	}
	
	// A value of 1 means no warping (out = in)
	// A value > 1 means input is less sensitive near zero position and more sensitive near the end positions
	// A value of < 1 means the input is more sensitive near zero, etc.
	public void setWarp(double warp) {
		this.warp = warp;
	}

	public double warp(double input) {
		if (input < 0.0) {
			if (input < -1.0) {
				input = -1.0;
			}
			
			return -Math.pow(-input, warp);
		} else {
			if (input > 1.0) {
				input = 1.0;
			}
			
			return Math.pow(input, warp);
		}
	}
	
	public double getWarp() {
		return warp;
	}
}
