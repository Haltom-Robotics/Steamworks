package org.usfirst.frc6133.Steamworks.commands;

import org.usfirst.frc6133.Steamworks.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FindLift extends Command {
	
	private boolean isDone = false;


    public FindLift() {
    	//requires(Robot.drivetrain);
    }

    protected void initialize() {
    	Robot.grip.imgProc();
    }

    protected void execute() {
    	isDone = true;
    }

    protected boolean isFinished() {
        return isDone;
    }

    protected void end() {
    	String report = "DeltaX: " + Robot.grip.reportDeltaX();
    	report += "\nR1 = " + Robot.grip.w1 + " x " + Robot.grip.h1;
    	report += "\nR2 = " + Robot.grip.w2 + " x " + Robot.grip.h2;
    	System.out.println(report);
    	//Scheduler.getInstance().removeAll();
    }

    protected void interrupted() {
    	end();
    }
}
