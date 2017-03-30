// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc6133.Steamworks.subsystems;

import org.usfirst.frc6133.Steamworks.RobotMap;
import org.usfirst.frc6133.Steamworks.commands.*;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Shooter extends Subsystem {
	private final Servo servo = RobotMap.shooterServo;
	
	public void initDefaultCommand() {
		setDefaultCommand(new FuelClose());
	}
	
	public void stage1() {
		servo.set(0.);
	}
	
	public void stage2() {
		servo.set(.66);
	}
	
	public void stage3() {
		servo.set(1.);
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * Old Shooter class code, preserved in case we need to revert
	 
	public final Victor leftMotor = RobotMap.shooterLeftMotor;
	public final Victor rightMotor = RobotMap.shooterRightMotor;
	public final RobotDrive launcher = RobotMap.shooterLauncher;
	public final Victor beltMotor = RobotMap.shooterBelt;
	public final Victor aimMotor = RobotMap.shooterAim;
	
	private double beltSpeed = 0;
	private double launcherSpeed = 0;

    public void initDefaultCommand() {
    	//setDefaultCommand(new StopShooter());
    }
    
    private void updateBeltSpeedFromDashboard() {
    	String b = RobotMap.beltSpeed.getSelected();
    	    	
    	if (b == "100")
    		beltSpeed = -1;
    	else if (b == "85")
    		beltSpeed = -.85;
    	else if (b == "70")
    		beltSpeed = -.7;
    	else if (b == "50")
    		beltSpeed = -.5;
    	else
    		beltSpeed = -.25;
    	
    }
    
    private void updateLauncherSpeedFromDashboard() {
    	String l = RobotMap.launcherSpeed.getSelected();
    	
    	if (l == "100")
    		launcherSpeed = 1;
    	else if (l == "85")
    		launcherSpeed = .85;
    	else if (l == "70")
    		launcherSpeed = .7;
    	else if (l == "50")
    		launcherSpeed = .5;
    	else
    		launcherSpeed = .25;
    }
    
    public void beltOn() {
    	updateBeltSpeedFromDashboard();
    	beltMotor.set(beltSpeed);
    }
    
    public void beltOff() {
    	beltMotor.set(0);
    }
    
    public void launcherOn() {
    	updateLauncherSpeedFromDashboard();
    	launcher.drive(launcherSpeed, 0);
    }
    
    public void launcherOff() {
    	launcher.drive(0, 0);
    }
    
    public void stop() {
    	beltOff();
    	launcherOff();
    }
    */
}

