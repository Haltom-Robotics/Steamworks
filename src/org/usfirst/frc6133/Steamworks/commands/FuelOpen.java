package org.usfirst.frc6133.Steamworks.commands;

import org.usfirst.frc6133.Steamworks.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FuelOpen extends Command {
	
	public FuelOpen() {
		requires(Robot.shooter);
	}

	protected boolean isFinished() {
		return false;
	}
	
	protected void initialize() {
		Robot.shooter.stage3();
	}
	
	protected void execute() {
		Robot.shooter.stage3();
	}
	
	protected void end() {
		Robot.shooter.stage3();
	}
	
	protected void interrupted() {
		end();
	}
}
