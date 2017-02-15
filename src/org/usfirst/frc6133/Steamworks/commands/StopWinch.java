package org.usfirst.frc6133.Steamworks.commands;

import org.usfirst.frc6133.Steamworks.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopWinch extends Command {


    public StopWinch() {
    	requires(Robot.winch);
    }

    protected void initialize() {
    	Robot.winch.stop();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.winch.stop();
    }

    protected void interrupted() {
    	end();
    }
}
