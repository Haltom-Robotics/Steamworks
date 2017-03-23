package org.usfirst.frc6133.Steamworks.commands;

import org.usfirst.frc6133.Steamworks.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Fire extends Command {
	private boolean prepLauncher = true;

    public Fire() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    	//Robot.shooter.launcherOn();
    	
    }

    protected void execute() {
    	if (prepLauncher) {
    		prepLauncher = false;
    		//Robot.shooter.launcherOn();
    		Timer.delay(0.5);
    	} else {
    		//Robot.shooter.launcherOn();
    		//Robot.shooter.beltOn();
    		Timer.delay(0.005);
    	}
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
