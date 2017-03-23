package org.usfirst.frc6133.Steamworks.commands;

import org.usfirst.frc6133.Steamworks.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopShooter extends Command {


    public StopShooter() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    	//Robot.shooter.stop();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	//Robot.shooter.stop();
    }

    protected void interrupted() {
    	end();
    }
}
