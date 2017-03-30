package org.usfirst.frc6133.Steamworks.commands;

import org.usfirst.frc6133.Steamworks.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FuelRelease extends Command {
	
	public FuelRelease() {
		requires(Robot.shooter);
	}
	
	protected void initialize() {
		Robot.shooter.stage2();
	}
	
	protected void execute() {
		Robot.shooter.stage2();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.shooter.stage2();
	}
	
	protected void interrupted() {
		end();
	}

}
