package org.usfirst.frc6133.Steamworks.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc6133.Steamworks.Robot;

public class FuelClose extends Command {

	public FuelClose() {
		requires(Robot.shooter);
	}

	protected boolean isFinished() {
		return false;
	}
	
	protected void initialize() {
		Robot.shooter.stage1();
	}
	
	protected void execute() {
		Robot.shooter.stage1();
	}
	
	protected void end() {
		Robot.shooter.stage1();
	}
	
	protected void interrupted() {
		end();
	}
}
