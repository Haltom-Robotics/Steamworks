package org.usfirst.frc6133.Steamworks.commands;

import org.usfirst.frc6133.Steamworks.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbRope extends Command {


    public ClimbRope() {
    	requires(Robot.winch);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.winch.triggerWinch();
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
